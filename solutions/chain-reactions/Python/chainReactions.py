from sys import exit


class tree():
    def __init__(self, id: int, data: int) -> None:
        self.id = id
        self.children = []
        self.data = data

    def has_children(self) -> bool:
        return len(self.children) != 0

    def __str__(self, level=0):
        result = ("\t" * level) + repr(self.id) + ": " + repr(self.data) + "\n"

        for child in self.children:
            result += child.__str__(level + 1)

        return result


class chain():
    def __init__(self) -> None:
        self.my_machines = []
        self.last_id = 0
        self.pointing_to_void = []

    def insert_machine(self, points_to: int, fun_factor: int):
        new_machine = tree(self.last_id + 1, fun_factor)
        self.last_id += 1

        self.my_machines.append(new_machine)

        if points_to == 0:
            self.pointing_to_void.append(new_machine)
        else:
            try:
                self.my_machines[points_to - 1].children.append(new_machine)
            except IndexError:
                print("Index's out of range. :'(")
                print(f"We have {len(self.my_machines)} machines and you tried to point to {points_to}.")

                exit(1)

    def __str__(self) -> str:
        result = ''
        for machine in self.pointing_to_void:
            result += str(machine)

        return result


# El código aquí es un ASCO. Es difícil de entender y lo sé. :'(
# Será mucho más fácil que expliquemos cómo funciona en la función
# `DFS_children_old` de abajo.
def DFS_children(machine: tree):
    fun_of_dead_branches = []
    pile_fun_to_compare = [(0, [])]
    machines_watch_later = [machine]

    while machines_watch_later:
        current_machine = machines_watch_later.pop()

        if current_machine is None:
            parent_fun, children_fun = pile_fun_to_compare.pop()
            lowest_fun = min(children_fun)
            children_fun.remove(lowest_fun)

            pile_fun_to_compare[-1][1].append(max(
                parent_fun, lowest_fun
            ))
            for child_fun in children_fun:
                fun_of_dead_branches.append(child_fun)
        elif current_machine.has_children():
            machines_watch_later.append(None)
            for children in current_machine.children:
                machines_watch_later.append(children)
            
            pile_fun_to_compare.append(
                (current_machine.data, [])
            )
        else:
            if not pile_fun_to_compare:
                return current_machine.data, []
            
            pile_fun_to_compare[-1][1].append(current_machine.data)

    return pile_fun_to_compare[0][1][0] + sum(fun_of_dead_branches)


def DFS(my_chain: chain):
    current_fun = 0

    for machine in my_chain.pointing_to_void:
        current_fun += DFS_children(machine)

    return current_fun


def read_chain_from_input():
    machines = int(input()) # xd
    my_chain = chain()

    fun_list = map(int, input().split(' '))
    connections_list = map(int, input().split(' '))

    for connection, fun in zip(connections_list, fun_list):
        my_chain.insert_machine(connection, fun)

    return my_chain


def main():
    Times = int(input())

    for i in range(Times):
        my_chain = read_chain_from_input()
        print(f"Case #{i + 1}: {DFS(my_chain)}")

if __name__ == '__main__':
    main()


# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
# # # # # # # # # # # # # INTENTO ANTERIOR FALLIDO. # # # # # # # # # # # # # #
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
"""
Para entender esto, imaginemos un árbol del siguiente estilo:
                6
              / | \
             4  2  8
El nodo de diversión 6 tiene como "hijos" a nodos de diversión 4, 2 y 8. De
entre los hijos, escogeremos al nodo de MENOR diversión. Ése nodo será el que
ejecutaremos primero. Así tendremos la ejecución (2 -> 6 -> Ø) y una diversión
total de 6. ¿Ves por qué tomamos al menor?

Y no sólo eso. Al tomar al 2, la rama de diversión 4 se quedará con ésa
diversión. Ya no podrá ejecutar nuevamente al 6. A ésta rama del 4 la llamaré
"rama muerta." Exactamente lo mismo pasa con la rama de diversión 8, es una
rama muerta. Al final del código, sumaremos la ejecución del primer nodo junto
con todos los puntos de diversión de las ramas muertas.
"""
def DFS_children_old(current_node: tree):
    fun_of_dead_branch = []

    if len(current_node.children) == 0:
        # Si el nodo no tiene hijos, regresamos el valor del nodo.
        return current_node.data, fun_of_dead_branch
    else:
        # De todo nodo, estaremos comparando la diversión de todos sus hijos.
        results = [DFS_children(my_child) for my_child in current_node.children]

        # Nuestra meta es encontrar el hijo con la menor diversión.
        lowest_fun = 10**10
        id_lowest_fun = -1
        for i, (max_fun, dead_branches) in enumerate(results):
            if max_fun < lowest_fun:
                id_lowest_fun = i
                lowest_fun = max_fun

            # Aprovecharemos para recolectar todas las ramas muertas de los hijos.
            fun_of_dead_branch += dead_branches

        # Teniendo ya escogido al hijo con la menor diversión, lo tomaremos
        # para compararlo con el padre. Todos los demás hijos se convertirán en
        # ramas muertas. (Muajajaja.)
        for i in range(len(results)):
            if i == id_lowest_fun:
                continue

            fun_of_dead_branch.append(results[i][0])

        # Regresaremos la comparación entre el padre y el hijo con la menor
        # diversión, junto con todas las ramas muertas que hemos recolectado.
        return max(
            current_node.data,
            lowest_fun
        ), fun_of_dead_branch
    

def DFS_old(my_chain: chain):
    current_fun = 0

    for machine in my_chain.pointing_to_void:
        fun_of_child, fun_of_dead_child = DFS_children(machine)

        # Sumaremos primero la diversión de la primera ejecución...
        # Creo que éste `max` está de más... Apenas lo vi.
        current_fun += max(machine.data, fun_of_child)
        # Para después sumar todas las ramas muertas.
        current_fun += sum(fun_of_dead_child)

    return current_fun

"""¿Qué salió mal de ésta implementación?
- La recursividad. (Línea 151.)

El Test Set 2 lo fallaba por alcanzar el límite de profundidad en cuanto a
recursividad. Aunque sea una solución bonita, no fue suficiente.

El asqueroso código que está arriba fue mi intento de hacerlo no-recursivo.
Ojalá el código recursivo te haya ayudado a entender qué fue lo que hice. :)
"""

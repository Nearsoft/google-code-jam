// tsc main.ts && node main.js
function main() {
    console.log("Hola");

    const data =
        [
            "1",
            "4",
            "1 2 3 4",
            "0 1 2 2"
        ]
    /*
    [
        "1",
        "5",
        "1 2 3 2 4",
        "0 1 1 3 3"
    ]
    */
    /*
    [
        "3",
        "4",
        "60 20 40 50",
        "0 1 1 2",
        "5",
        "3 2 1 4 5",
        "0 1 1 1 0",
        "8",
        "100 100 100 90 80 100 90 100",
        "0 1 2 1 2 3 1 3",
    ]
    */
    let tests = parseInt(data[0]);
    for (let i = 1; i <= tests * 3; i++) {
        solution(data[i], data[++i].split(" "), data[++i].split(" "));
    }
}

function solution(numElement: string, elementsData: string[], pointingData: string[],) {
    // console.log(numElement, elementsData, pointingData);
    var mappedData = createData(parseInt(numElement) + 1);
    for (let nodo = 0; nodo < elementsData.length; nodo++) {
        mappedData[pointingData[nodo]][nodo + 1] = new TreeNode(parseInt(elementsData[nodo]));
        //parseInt(elementsData[nodo]);
        //new TreeNode(parseInt(elementsData[nodo]));
    }
    var sum = 0;
    for (let pointing = parseInt(numElement); pointing >= 0; pointing--) {
        let toAdd: Array<TreeNode> = [];
        for (let node = parseInt(numElement); node >= 0; node--) {
            if (mappedData[pointing][node] != -1) {
                //console.log("Actual ", mappedData[pointing][node]);
                //console.log("Apuntando a: ", node, pointing);

                // console.log(mappedData[parseInt(pointingData[pointing-1])][pointing]);
                if (pointing != 0) {
                    toAdd.push(mappedData[pointing][node]);
                    mappedData[parseInt(pointingData[pointing - 1])][pointing].addNode(mappedData[pointing][node]);
                    if (mappedData[parseInt(pointingData[pointing - 1])][pointing].max < mappedData[pointing][node].max)
                        mappedData[parseInt(pointingData[pointing - 1])][pointing].max = mappedData[pointing][node].max
                    // console.log("Apuntando a ", mappedData[parseInt(pointingData[pointing - 1])][pointing]);
                }
                else {
                    // console.log(mappedData[pointing][node]);
                    sum += analizeTree(mappedData[pointing][node]);
                }
            }
        }
        if (toAdd.length == 1) {
            if (mappedData[parseInt(pointingData[pointing - 1])][pointing].value < toAdd[0].value) {
                mappedData[parseInt(pointingData[pointing - 1])][pointing] = toAdd[0];
            }
        }
    }
    console.log("Solucion final: ", sum);
}

function analizeTree(tree: TreeNode): number {
    // console.log("Analizando: ", tree);
    let sum = 0;
    if (tree.children.length == 0) {
        // console.log("Suma hasta el momento", tree.max);
        return tree.max;
    } else {
        let minNode: TreeNode | null = null;
        tree.children.forEach(subtree => {
            if (minNode == null) {
                minNode = subtree;
            } else {
                console.log("Datos", minNode.max, subtree.max);

                if (minNode.max > subtree.max) {
                    console.log("Analizando nuevo: ", minNode);
                    sum += analizeTree(minNode);
                    minNode = subtree;
                } else {
                    sum += analizeTree(subtree);
                }
            }
        });
        // console.log("El nodo minimo es: ", minNode, sum);
        return sum + ((minNode!.max > tree.value) ? minNode!.max : tree.value);
    }
}

function createData(numElem: number) {
    var mappedData = new Array<Array<any>>;
    for (let x = 0; x < numElem; x++) {
        var rowInfo = new Array<number>();
        for (let y = 0; y < numElem; y++) {
            rowInfo.push(-1);
        }
        mappedData.push(rowInfo);
    }
    return mappedData;
}

class TreeNode {
    public children: Array<TreeNode> = [];
    public value: number;
    public max: number;
    public hasMoreChildren: boolean;

    constructor(value: number) {
        this.value = value;
        this.max = value;
    }

    public addNode(node: TreeNode) {
        this.children.push(node);
    }
}

main();
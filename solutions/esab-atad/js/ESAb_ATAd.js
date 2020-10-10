/***
 * EncorAcademy 2020B
 * ESAb ATAd
 * Google Code Jam 2020 
 * 
 * */
const rl = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

function flip(arr){
  for(let i = 0; i < arr.length; i++)
  {
      arr[i] ^= 1;
  }
  return arr;
}

async function query(i, count) {
    if(i !== null)
    {
        console.log(i+1);
    }
    else
    {
        console.log(1);
    }
    rl.resume();
    let input = await getInput();
    rl.pause();
    return [Number(input), count+1];
}

async function check(result){
    // unir arreglo de resultado en un string
    const res = result.join('');
    // recibe input del juez
    // ingresa "Y" cuando el resultado es correcto
    // ingresa "N" cuando el resultado es incorrecto
    console.log(res);
    rl.resume();
    const ok = await getInput();
    rl.pause();
    // rl.close();
    //verifica que el resutado sea correcto
    if (ok != "Y") // error
    {
        process.exit(1);
    }
}

async function esab_atad(B)
{
    //result arreglo con la longitud de B
    //count contador de el numero de queries realizados
    let result = Array.from({length: B}, (_, i) => 0);
    let count = 0;
    let flip_i = null;
    let reverse_i = null;

    let lengthFloor = Math.floor(B/2);
    let flip_res = null;
    let reverse_res = null;

    //para cada elemento del numero de Bits / 2 (redondeado hacia abajo)
    for(let i = 0; i < lengthFloor; i++){
        // console.log(i);
        if (count%10 === 0 && i!=0){
            // recibe el flag del tester y count + 1 y los guardar en flip_res y count
            [flip_res, count] = await query(flip_i, count);
            //recibe el flag del tester y count + 1 y los guardar en reverse_res y count
            [reverse_res, count] = await query(reverse_i, count)
            if (flip_i !== null && (result[flip_i] ^ flip_res)){
               //manda llamar la funcion flip y modifica todo el arreglo
               result = flip(result);
            }
            if (reverse_i !== null && (result[reverse_i] ^ reverse_res))
            {
                //invierte el arreglo
                result = result.reverse();
            }
                
        }
        // Regresa el valor que contiene el input original en la posicion i
        // y lo asigna en la posicion i del arreglo result
        [result[i], count] = await query(i, count);
        // Regresa el valor que contiene el input original en la posicion B-i-1
        // y lo asigna en la posicion B-i-1 del arreglo result
        [result[B-i-1], count] = await query(B-i-1, count);
        if (result[i] === result[B-i-1])
        {
            flip_i = i;
        }
        else
        {
            reverse_i = i;
        }
    }
    await check(result);
}

function getInput()
{
    return new Promise((resolve, reject) => {
      rl.once("line", (answer) => {
        resolve(answer)
      })
    })
}
  

async function main() {
    const res = await getInput();

    const T = res.split(' ')[0];
    const B = res.split(' ')[1];
    rl.pause();
    for (let test = 0; test<T;test++)
    {
        await esab_atad(B);
    }
    process.exit(0);
}
main()
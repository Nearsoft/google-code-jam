// This is a line by line conversion of the python solution for twisty little passages

declare var require: any;
declare var process: any;
const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
})
const solve = async () => {
  const T = parseInt(await readLine())
  for (let it = 0; it < T; it++) {
    const [N, K] = (await readLine()).split(' ').map(Number)
    const Rm:any = {}
    const E:any = {}
    let [r, p] = (await readLine()).split(' ').map(Number)
    Rm[r] = p
    for (let i = 0; i < K; i++) {
      if (i % 2 === 0) {
        console.log('W')
        let [r, p] = (await readLine()).split(' ').map(Number)
        E[r] = p - 0.5
      } else {
        const r = Math.random()
        console.log(`T ${r}`)
        let [p] = (await readLine()).split(' ').map(Number)
        Rm[r] = p
      }
    }
    let guess = 0
    for (const r in Rm) {
      guess += Rm[r]
    }
    guess *= N / (2 * Object.keys(Rm).length)
    for (const e in E) {
      if (!(e in Rm)) {
        guess += E[e] / 2
      }
    }
    console.log(`E ${Math.round(guess)}`)
    rl.close()
  }
}
const readLine = () =>
  new Promise<string>(resolve => {
    rl.once('line', resolve)
  })
solve()
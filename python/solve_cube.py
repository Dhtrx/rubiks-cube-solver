import kociemba
import sys

def solve(cube):
    return kociemba.solve(cube)

if __name__=="__main__":
    scramble=sys.argv[1]
    print(solve(scramble))
import numpy as np
import matplotlib.pyplot as plt

DNA_SIZE = 20  # DNA length
POP_SIZE = 100  # population size
CROSS_RATE = 0.8  # mating probability (DNA crossover)
MUTATION_RATE = 0.03  # mutation probability
N_GENERATIONS = 100
X_BOUND = [0, 10]  # x upper and lower bounds
n=10
c=[1,1.5,-2.5,2.5,-4.5,1,1.5,-3,0.5,-3.5]
L=[3.7,7.8,4.5,2.6,9.1,0.5,4.8,6.1,6.8,5.5]

def F(x):
    sum=0
    for i in range(n):
        sum+=c[i]*np.exp(-(x-L[i]) ** 2)
    return sum


# find non-zero fitness
def get_fitness(pred): return pred + 0.001 - np.min(pred)


# convert binary DNA to decimal and normalized
def translateDNA(pop): return pop.dot(2 ** np.arange(DNA_SIZE)[::-1]) / float(2 ** DNA_SIZE - 1) * X_BOUND[1]


def select(pop, fitness):  # nature selection
    idx = np.random.choice(np.arange(POP_SIZE), size=POP_SIZE, replace=True,
                           p=fitness / fitness.sum())
    return pop[idx]


def crossover(parent, pop):
    if np.random.rand() < CROSS_RATE:
        i_ = np.random.randint(0, POP_SIZE, size=1)  # select another individual from pop
        cross_points = np.random.randint(0, 2, size=DNA_SIZE).astype(np.bool)  # choose crossover points
        print(cross_points)
        print(pop[i_])
        parent[cross_points] = pop[i_, cross_points]  # mating and produce one child
        print(parent)
    return parent


def mutate(child):
    for point in range(DNA_SIZE):
        if np.random.rand() < MUTATION_RATE:
            child[point] = 1 if child[point] == 0 else 0
    return child


pop = np.random.randint(2, size=(POP_SIZE, DNA_SIZE))


plt.ion()  # something about plotting
x = np.linspace(*X_BOUND, 200)
plt.plot(x, F(x))

for t in range(N_GENERATIONS):
    F_values = F(translateDNA(pop))

    # something about plotting
    if 'sca' in globals(): sca.remove()
    sca = plt.scatter(translateDNA(pop), F_values, s=20, lw=0, c='red', alpha=0.5);
    plt.pause(0.01)

    # GA part (evolution)
    print(F_values)
    fitness = get_fitness(F_values)
    pop = select(pop, fitness)
    pop_copy = pop.copy()
    bestdna=pop_copy[0]
    bestx=translateDNA(pop[0])
    bestfit=F(translateDNA(pop[0]))
    for z in range(POP_SIZE):
        if F(translateDNA(pop[z]))>bestfit:
            bestdna = pop_copy[z]
            bestx = translateDNA(pop[z])
            bestfit = F(translateDNA(pop[z]))
    print(t+1,"times is: ",bestdna,bestx,bestfit)


    for parent in pop:
        child = crossover(parent, pop_copy)
        child = mutate(child)
        parent[:] = child
plt.ioff();
plt.show()

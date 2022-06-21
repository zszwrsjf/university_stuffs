import numpy as np
import matplotlib.pyplot as plt

DNA_SIZE = 10  # DNA length
POP_SIZE = 100  # population size
CROSS_RATE = 0.8  # mating probability
MUTATION_RATE = 0.5  # mutation probability
N_GENERATIONS = 100
x=[9.9, 4.2, 4, 2.1, 0.2, 4, 7.7, 1.2, 3.2, 0.6]
y=[3.7, 7.8, 4.5, 2.6, 9.1, 0.5, 4.8, 6.1, 6.8, 5.5]

def F(X):
    sum = np.sqrt(x[int(X[0])]**2+y[int(X[0])]**2)+np.sqrt(x[int(X[DNA_SIZE-1])]**2+y[int(X[DNA_SIZE-1])]**2)
    for i in range(DNA_SIZE-1):
        sum += np.sqrt((x[int(X[i])]-x[int(X[i+1])])**2+(y[int(X[i])]-y[int(X[i+1])])**2)
    return sum


def get_fitness(pred): return 1/(pred + 0.001 - np.min(pred))

def select(pop, fitness):  # nature selection wrt pop's fitness
    idx = np.random.choice(np.arange(POP_SIZE), size=POP_SIZE, replace=True,
                           p=fitness / fitness.sum())
    return pop[idx]

def crossover(parent, pop):  # mating process (genes crossover)
    if np.random.rand() < CROSS_RATE:
        i_ = np.random.randint(0, POP_SIZE, size=1)  # select another individual from pop
        a = np.random.randint(0,DNA_SIZE,size=1)
        b = np.random.randint(a,DNA_SIZE,size=1)  # choose the bound of changing
        n=b-a+1
        t1 = 0
        t2=0
        for q in range(n[0]):
            while(parent[t1]<a or parent[t1]>b):t1+=1
            while(pop[i_,t2]<a or pop[i_,t2]>b): t2+=1
            t=parent[t1]
            parent[t1]=pop[i_,t2]
            pop[i_, t2]=t
            t1+=1
            t2+=1
    return parent

def mutate(child):
    if np.random.rand() < MUTATION_RATE:
        a=np.random.randint(0,10)
        b=np.random.randint(0,10)
        t=child[a]
        child[a]=child[b]
        child[b]=t
    return child

pop = np.zeros((POP_SIZE, DNA_SIZE))
for j in range(POP_SIZE):
    for i in range(DNA_SIZE):
        pop[j][i]=i
    np.random.shuffle(pop[j])

plt.ion()  # something about plotting
plt.scatter(x, y,c='g')

for t in range(N_GENERATIONS):
    F_values=[]
    for i in range(POP_SIZE):
        F_value = F(pop[i])
        F_values.append(F_value)
    F_values=np.array(F_values)



    # GA part (evolution)
    fitness = get_fitness(F_values)
    pop = select(pop, fitness)
    pop_copy = pop.copy()
    bestdna=pop_copy[0]
    bestx=pop[0]
    bestfit=F(pop[0])
    for z in range(POP_SIZE):
        if F(pop[z])<bestfit:
            bestdna = pop_copy[z]
            bestx = pop[z]
            bestfit = F(pop[z])
    print(t+1,"times is: ",bestdna,bestfit)



    for parent in pop:
        child = crossover(parent, pop_copy)
        child = mutate(child)
        parent[:] = child
fx = [0]
fy = [0]
for i in range(DNA_SIZE):
    fx.append(x[int(bestdna[i])])
    fy.append(y[int(bestdna[i])])
fx.append(0)
fy.append(0)
sca=plt.plot(fx,fy)
plt.ioff()
plt.show()
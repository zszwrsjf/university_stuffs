import numpy as np
import random
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

n = 20
c = [1, 1.5, -2.5, 2.5, -4.5, 1, 1.5, -3, 0.5, -3.5, 3, 1, -5, 2, 3, 4, -2, 1, 4, -2]
Lx = [3.7, 7.8, 4.5, 2.6, 9.1, 0.5, 4.8, 6.1, 6.8, 5.5, 9.9, 4.2, 4, 2.1, 0.2, 4, 7.7, 1.2, 3.2, 0.6]
Ly = [6.1, 6.8, 5.5, 7.8, 4.5, 2.6, 6.1, 0.2, 4, 3.7, 7.8, 1.2, 3.2, 0.6 , 1.4 , 7.9 , 5.5 , 2.3 , 4.6 ,1]


class PSO():
    def __init__(self, pN, dim, max_iter):
        self.w = 1
        self.c1 = 2
        self.c2 = 2.1
        self.pN = pN
        self.dim = dim
        self.max_iter = max_iter  #
        self.X = np.zeros((self.pN, self.dim))
        self.V = np.zeros((self.pN, self.dim))
        self.pbest = np.zeros((self.pN, self.dim))
        self.gbest = np.zeros((1, self.dim))
        self.p_fit = np.zeros(self.pN)
        self.fit = 0
        self.x_bound=[0,10]
        self.y_bound=[0,10]


    def F(self, X ):
        sum=0
        for z in range(n):
            sum+=c[z]*np.exp(-((X[0]-Lx[z])**2+(X[1]-Ly[z])**2))
        return sum

    def Ft(self,x,y):
        sum = 0
        for z in range(n):
            sum += c[z] * np.exp(-((x-Lx[z])**2+(y-Ly[z])**2))
        return sum


    def init_Population(self):
        for i in range(self.pN):
            for j in range(self.dim):
                self.X[i][j] = random.uniform(0, 1)
                self.V[i][j] = random.uniform(0, 1)
            self.pbest[i] = self.X[i]
            tmp = self.F(self.X[i])
            self.p_fit[i] = tmp
            if tmp < self.fit:
                self.fit = tmp
                self.gbest = self.X[i]



    def iterator(self):
        plt.ion()
        fig = plt.figure()
        ax = Axes3D(fig)
        x = np.linspace(*self.x_bound, 200)
        y = np.linspace(*self.y_bound, 200)
        x, y = np.meshgrid(x, y)
        z = self.Ft(x, y)

        ax.plot_surface(x, y, z, rstride=1, cstride=1)

        bestx=[]
        bestf=0
        for t in range(self.max_iter):
            px=self.X[:,0]
            py=self.X[:,1]
            pz=self.Ft(self.X[:,0],self.X[:,1])
            sca=ax.scatter(px,py,pz,c='r')
            plt.pause(0.1)
            if t!=self.max_iter-1:
                sca.remove()
            for i in range(self.pN):
                temp = self.F(self.X[i])
                if temp > self.p_fit[i]:
                    self.p_fit[i] = temp
                    self.pbest[i] = self.X[i]
                    if self.p_fit[i] > self.fit:
                        self.gbest = self.X[i]
                        self.fit = self.p_fit[i]
            for i in range(self.pN):
                tempv = (self.w * self.V[i] + self.c1 * random.random() * (self.pbest[i] - self.X[i]) + self.c2 * random.random() * (self.gbest - self.X[i])).copy()
                tempx = (self.X[i] + tempv).copy()
                if tempx[0] < self.x_bound[1] and tempx[0]>self.x_bound[0] and tempx[1]>self.y_bound[0] and tempx[1] < self.y_bound[1]:
                    self.V[i]=tempv.copy()
                    self.X[i]=tempx.copy()
            if self.F(self.gbest) > bestf:
                bestx=self.gbest.copy()
                bestf=self.F(self.gbest)

            print(t+1,"times the max is in",bestx,"which is",bestf)

my_pso = PSO(pN=30, dim=2, max_iter=100)
my_pso.init_Population()
fitness = my_pso.iterator()
plt.ioff()
plt.show()
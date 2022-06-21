import numpy as np
import pandas as pd
import xlrd
from sklearn.preprocessing import LabelEncoder
import matplotlib.pyplot as plt

#拉数据出来溜
inputfile="suika3.0.xlsx"
data_origin=pd.read_excel(inputfile,'Sheet1')
x=np.array([list(data_origin[u'色泽']),list(data_origin[u'根蒂']),list(data_origin[u'敲声']),list(data_origin[u'纹理']),list(data_origin[u'脐部']),list(data_origin[u'触感']),list(data_origin[u'密度']),list(data_origin[u'含糖率'])])
y=np.array([list(data_origin[u'好瓜'])])
xname=["color","root","knocksound","vein","umbilical","touch","density","sugar"]
xvaris=[(['a']*2) for i in range(6)]
#print(xvaris)
x=x.T
y=y.T

#数据量化
for i in range(17):
    if y[i,0] in '是':
        y[i,0]=1
    else: y[i,0]=0
le=LabelEncoder()
for i in range(6):
    le.fit(x[:,i])
    xvaris[i]=list(le.classes_)
    x[:,i]=le.transform(x[:,i])

#数据矩阵换list后将str转float
x=list(x)
for i in range(17):
    x[i]=list(x[i])
y=list(y)
for i in range(17):
    for j in range(8):
        x[i][j]=float(x[i][j])
y=[float(t) for t in y]
#print(xvaris)
#print(x)
#print(y)
for i in range(17):
    x[i]=np.array(x[i])
y=np.array(y)


def basic(wyo,wso,thyo):
    #基本神经网络
    #初始参数设置     #10个隐层      #初始值必须随机！！！
    wy=wyo.copy()
    ws=wso.copy()
    thy=thyo.copy()
    ths=0
    ita=0.5
    t=0#总次数
    tt=0#成功次数
    flag=1
    ere=[]

    while(flag):
        for i in range(17):
            t += 1
            outy=1/(1+np.exp(thy-np.dot(x[i],wy.T)))
            outs=1/(1+np.exp(ths-np.dot(outy,ws)))
            new=0
            for j in range(17):
                outyt = 1 / (1 + np.exp(thy - np.dot(x, wy.T)))
                outst = 1 / (1 + np.exp(ths - np.dot(outyt, ws)))
                new+=(outst[j]-y[j])*(outst[j]-y[j])/2
            g=outs*(1-outs)*(y[i]-outs)
            e=outy*(1-outy)*g*ws.T
            ws+=ita*g*outy.T
            ths-=ita*g
            wy+=ita*e.T*x[i]
            thy-=ita*e
            print(t,'times',new)
            ere.append(new)
        if(new<0.01):
            tt+=1
            if(tt==100):
                 for i in range(17):
                     outy = 1 / (1 + np.exp(thy - np.dot(x[i], wy.T)))
                     outs = 1 / (1 + np.exp(ths - np.dot(outy, ws)))
                     print(i+1,outs)
                 flag=0
        else:
            tt=0#重来
    ere=np.array(ere)
    return ere

def accum(n,wyo,wso,thyo):
    #累积神经网络
    # 初始参数设置   #初始值必须随机！！！
    wy = wyo.copy()
    ws = wso.copy()
    thy = thyo.copy()
    ths = 1
    ita = 1
    t = 0  # 总次数
    tt = 0  # 成功次数
    flag = 1
    ere=[]

    while (flag):
        t += 1
        new = 0
        outy = 1 / (1 + np.exp(thy - np.dot(x, wy.T)))
        outs = 1 / (1 + np.exp(ths - np.dot(outy, ws)))
        #print(outs)
        for i in range(17):
            new += (outs[i] - y[i]) * (outs[i] - y[i]) / 2
        #print(new)
        g = outs * (1 - outs) * (y.reshape(17,1)- outs)
        e = outy * (1 - outy) * g * ws.T
        for i in range(17):
            ws += ita * g[i] * outy[i].reshape(n,1)
            ths -= ita * g[i]
            wy += ita * np.dot(e[i].reshape(n,1),x[i].reshape(1,8))
            thy -= ita * e[i]
        print(t, 'times', new)
        ere.append(new)
        if (new < 0.01):
            tt += 1
            if (tt == 10):
                for i in range(17):
                    outy = 1 / (1 + np.exp(thy - np.dot(x[i], wy.T)))
                    outs = 1 / (1 + np.exp(ths - np.dot(outy, ws)))
                    print(i + 1, outs)
                flag = 0
        else:
            tt = 0  # 重来
    ere = np.array(ere)
    return ere

def fit(n,wyo,wso,thyo):
    #累积神经网络
    # 初始参数设置   #初始值必须随机！！！
    wy = wyo.copy()
    ws = wso.copy()
    thy = thyo.copy()
    ths = 1
    ita = 1
    t = 0  # 总次数
    tt = 0  # 成功次数
    flag = 1
    delta=0.25
    gt1=0
    gt2=0
    ere=[]

    while (flag):
        t += 1
        new = 0
        outy = 1 / (1 + np.exp(thy - np.dot(x, wy.T)))
        outs = 1 / (1 + np.exp(ths - np.dot(outy, ws)))
        #print(outs)
        for i in range(17):
            new += (outs[i] - y[i]) * (outs[i] - y[i]) / 2
        #print(new)
        g = outs * (1 - outs) * (y.reshape(17,1)- outs)
        e = outy * (1 - outy) * g * ws.T
        for i in range(8):
            gt1+=g[i]*g[i]/8
        for i in range(9):
            gt2+=g[8+i]*g[8+i]/9
        for i in range(17):
            if(i<8):
                c=ita/(delta+np.sqrt(gt1))
            else: c=ita/(delta+np.sqrt(gt2))
            ws += c* g[i] * outy[i].reshape(n, 1)
            ths -= c * g[i]
            wy += c * np.dot(e[i].reshape(n, 1), x[i].reshape(1, 8))
            thy -= c * e[i]
        print(t, 'times', new)
        ere.append(new)
        if (new < 0.01):
            tt += 1
            if (tt == 10):
                for i in range(17):
                    outy = 1 / (1 + np.exp(thy - np.dot(x[i], wy.T)))
                    outs = 1 / (1 + np.exp(ths - np.dot(outy, ws)))
                    print(i + 1, outs)
                flag = 0
        else:
            tt = 0  # 重来
    ere = np.array(ere)
    return ere

def plot (e1,e2):
    x1=np.linspace(1,len(e1),len(e1))
    x2=np.linspace(1,len(e2)*17,len(e2))
    plt.plot(x1,e1)
    plt.plot(x2,e2)
    plt.show()

def plot2(e1,e2):
    x1 = np.linspace(1, len(e1), len(e1))
    x2 = np.linspace(1, len(e2) , len(e2))
    plt.plot(x1, e1)
    plt.plot(x2, e2)
    plt.show()

if __name__ == "__main__":
    ere1=[]
    ere2=[]
    ere3=[]
    n=6
    wyo = np.random.rand(n, 8)
    wso = np.random.rand(n, 1)
    thyo = np.random.rand(1, n)
    #ere1=basic(wyo,wso,thyo)
    ere2=accum(n,wyo,wso,thyo)
    ere3=fit(n,wyo,wso,thyo)
    #plot(ere1,ere2)
    plot2(ere2,ere3)

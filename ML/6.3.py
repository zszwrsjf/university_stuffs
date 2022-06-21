import numpy as np
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
from sklearn.svm import SVC
from sklearn import metrics
from sklearn.neural_network import MLPClassifier
from sklearn import tree

#拉数据出来溜
filename="iris.data"
file = open(filename, 'r')
linelen=0
first=1
flag=0
data=[]
while 1:
    #按行读
    line = file.readline()
    if not line:
        break
    line=line.strip('\n')
    rowdata = line.split(',')
    data.append(rowdata)
data.pop(len(data)-1)
data=np.array(data)
x=data[:,0:4]
y=data[:,-1]

le=LabelEncoder()
le.fit(y)
y=le.transform(y)

x=list(x)
for i in range(150):
    x[i]=list(x[i])
y=list(y)
for i in range(150):
    x[i]=list(map(float,x[i]))
y=list(map(float,y))
for i in range(150):
    x[i]=np.array(x[i])
y=np.array(y)
x=np.array(x)

#所有调用sklearn的函数时候切分数据集保证二分类
def svm(x,y,type):
    if(type==1):#前两种花
        x=x[0:100]
        y=y[0:100]
    else:#后两种花
        x=x[50:150]
        y=y[50:150]
    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.3, random_state=0)
    if(type==1):
        clf_linear = SVC(C=1.0, kernel='linear')
        clf_linear.fit(x_train,y_train)
        expected = y_test
        predicted = clf_linear.predict(x_test)
        print(metrics.classification_report(expected, predicted))  # 输出分类信息
        label = list(set(y))  # 去重复，得到标签类别
        print(metrics.confusion_matrix(expected, predicted, labels=label))  # 输出混淆矩阵信息
        print("linear's accuracy is %f" % clf_linear.score(x_test,y_test))

    else:
        clf_rbf=SVC(C=1.0,class_weight=None, kernel='rbf',gamma="auto")
        clf_rbf.fit(x_train, y_train)
        expected = y_test
        predicted = clf_rbf.predict(x_test)
        print(metrics.classification_report(expected, predicted))  # 输出分类信息
        label = list(set(y))  # 去重复，得到标签类别
        print(metrics.confusion_matrix(expected, predicted, labels=label))  # 输出混淆矩阵信息
        print("Gauss's accuracy is %f" % clf_rbf.score(x_test, y_test))

def bp(x,y):
    x = x[50:150]
    y = y[50:150]
    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.3, random_state=0)
    clf = MLPClassifier(solver='lbfgs', alpha=1e-5, hidden_layer_sizes=(5, 2),
                        random_state=None)  # 神经网络输入为2，第一隐藏层神经元个数为5，第二隐藏层神经元个数为2，输出结果为2分类。
    clf.fit(x_train, y_train)
    expected = y_test
    predicted = clf.predict(x_test)
    print(metrics.classification_report(expected, predicted))  # 输出分类信息
    label = list(set(y))  # 去重复，得到标签类别
    print(metrics.confusion_matrix(expected, predicted, labels=label))  # 输出混淆矩阵信息
    print("bp's accuracy is %f" % clf.score(x_test, y_test))

def decisiontree(x,y):
    x = x[50:150]
    y = y[50:150]
    x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.3, random_state=0)
    clf = tree.DecisionTreeClassifier()
    clf = clf.fit(x_train, y_train)
    expected = y_test
    predicted = clf.predict(x_test)
    print(metrics.classification_report(expected, predicted))  # 输出分类信息
    label = list(set(y))  # 去重复，得到标签类别
    print(metrics.confusion_matrix(expected, predicted, labels=label))  # 输出混淆矩阵信息
    print("decisiontree's accuracy is %f" % clf.score(x_test, y_test))

if __name__=="__main__":
    svm(x,y,1)#线性
    svm(x,y,2)#高斯
    bp(x,y)
    decisiontree(x,y)

# Qos 预测

## 问题背景

## 方法简述

使用 5 种方法进行 Qos 预测。

### IMEAN

[代码](./imean.py)

使用某一个服务的所有已知数据的均值作为该服务未知值的预测值。 计算时在时间维度上取平均以消除时间影响。下述方法中，除了`NNCP`外，其他方法均使用相同的方法处理数据的时间纬度。

### UMEAN

[代码](./umean.py)

使用某一个用户所有已知数据的均值作为该用户未知值的预测值。

### UPCC

[代码](./upcc.py)

UPCC 是基于用户的协同过滤算法。
在预测某用户未知的 QOS 时，UPCC 使用目标服务的其他已知值的加权平均作为预测值。
此处的权值是该用户和其他用户的相似性。
相似性由属于用户的向量的夹角表征。属于用户的向量是该用户调用不同服务的 QOS 数值组成的向量。

参考文献

```
Shao, L., Zhang, J., Wei, Y., Zhao, J., Xie, B., & Mei, H. (2007). Personalized QoS prediction for web services via collaborative filtering. Proceedings - 2007 IEEE International Conference on Web Services, ICWS 2007, Icws, 439–446. https://doi.org/10.1109/ICWS.2007.140
```

### IPCC

[代码](./ipcc.py)

IPCC 是基于服务的协同过滤算法。
在预测某用户未知的 QOS 时，IPCC 使用目标服务对应的用户的其他已知 QOS 值的加权平均作为预测值。
此处的权值是该服务和其他服务的相似性。
相似性由属于服务的向量的夹角表征。
属于服务的向量是该服务被不同用户调用的 QOS 数值组成的向量。

### NNCP

[代码](./nncp.py)

NNCP 是基于张量分解的预测算法。
相对于前几种方法，NNCP 最大的特点是考虑了时间的影响。

参考文献

```
Zhang, W., Sun, H., Liu, X., & Guo, X. (2014). Temporal QoS-aware Web Service recommendation via Non-negative Tensor Factorization. WWW 2014 - Proceedings of the 23rd International Conference on World Wide Web, 585–595. https://doi.org/10.1145/2566486.2568001
```

## 测试方法与结果

随机在数据集中选取 20%的数据作为测试集.

```
python3 -m pip install numpy
python3 data.py
```

结果

| 方法  | MAE   | RMSE  |
| ----- | ----- | ----- |
| UMEAN | 1.866 | 3.336 |
| IMEAN | 4.628 | 3.336 |
| UPCC  | 1.609 | 3.854 |
| IPCC  | 1.348 | 3.306 |
| NNCP  | 3.927 | 8.670 |

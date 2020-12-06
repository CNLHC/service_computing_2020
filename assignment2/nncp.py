from abcmethod import Method
import numpy as np


class NNCP(Method):
    @classmethod
    def _Iterative(cls, U, S, T, I, J, K, getdata, rank):
        U = U.copy()
        for i in range(I-1):
            for l in range(rank):
                t1 = 0
                t2 = 0
                m1 = S[:, l].copy()
                m1.resize((J, 1))
                m1 = np.tile(m1, (1, K))
                m2 = T[:, l].copy()
                m2.resize((1, K))
                m2 = np.tile(m2, (J, 1))
                t1 = np.sum(getdata(i) * m1 * m2)
                if(t1 == 0):
                    continue
                for r in range(rank):
                    t2 += U[i, r]*np.dot(S[:, l], S[:, r]) * \
                        np.dot(T[:, l], T[:, r])
                U[i+1, l] = U[i, l] * t1 / t2
        return U

    @classmethod
    def _Reconstruct(cls, U, S, T, I, J, K, rank):
        def re(U, r, s1, s2):
            m = U[:, r].copy()
            m.resize(s1)
            return np.tile(m, s2)

        out = np.zeros((I, J, K))

        for r in range(rank):
            a = re(U, r, (I, 1, 1), (1, J, K))
            b = re(S, r, (1, J, 1), (I, 1, K))
            c = re(T, r, (1, 1, K), (I, J, 1))
            out += a*b*c
        return out

    @classmethod
    def predict(cls, data, shape=(142, 4500, 64), pcount=1):
        cls.rank = 20
        (I, J, K) = shape
        U = np.random.random_sample(size=(I, cls.rank))
        S = np.random.random_sample(size=(J, cls.rank))
        T = np.random.random_sample(size=(K, cls.rank))
        Ut = cls._Iterative(
            U, S, T, I, J, K, lambda x: data[x, :, :], cls.rank)
        St = cls._Iterative(
            S, U, T, J, I, K, lambda x: data[:, x, :], cls.rank)
        Tt = cls._Iterative(
            T, U, S, K, I, J, lambda x: data[:, :, x], cls.rank)
        out = cls._Reconstruct(Ut, St, Tt, I, J, K, cls.rank)
        MAE = cls.MAE(data, out, pcount)
        RMSE = cls.RMSE(data, out, pcount)
        print("MAE", MAE)
        print("RMSE", RMSE)
        return (MAE, RMSE)

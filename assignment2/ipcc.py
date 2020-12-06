
import numpy as np
from abcmethod import Method
import functools


class IPCC(Method):
    @classmethod
    def predict(cls, data, shape=(142, 4500, 64), pcount=1, ds=None):
        out = data.copy()
        time_inv_data = cls.nonzero_mean(data, axis=2)
        w = np.cov(np.transpose(time_inv_data))
        imean = cls.nonzero_mean(time_inv_data, axis=0)

        all_res = []

        batch_number = 10
        batch_size = int(4500/batch_number)
        for chunk in range(batch_number):
            chunk_imean = imean[chunk*batch_size:(chunk+1)*batch_size]
            chunk_w = w[:, chunk*batch_size:(chunk+1)*batch_size]

            t_w = np.tile(np.reshape(
                chunk_w, (1, 4500, batch_size)), (142, 1, 1))

            t_data = np.tile(np.reshape(time_inv_data,
                                        (142, 4500, 1)), (1, 1, batch_size))

            t_imean = np.tile(np.reshape(
                imean, (1, 4500, 1)), (142, 1, batch_size))

            t_imean_a3 = np.tile(np.reshape(
                chunk_imean, (1, batch_size)), (142, 1))

            t01 = np.sum((t_data - t_imean) * t_w, axis=1)
            t02 = np.sum(t_w, axis=1)
            t02[t01 == 0] = 1
            t1 = t01 / t02
            t_res = np.reshape(t_imean_a3+(t1), (142, batch_size))
            all_res.append(t_res)

        res = np.concatenate(all_res, axis=1)
        res = np.reshape(res, (142, 4500, 1))
        res = np.tile(res, (1, 1, 64))
        out[ds.mask] = 0
        res[~ds.mask] = 0
        out = out+res

        MAE = cls.MAE(data, res, pcount)
        RMSE = cls.RMSE(data, res, pcount)
        print(MAE, RMSE)
        return (MAE, RMSE)

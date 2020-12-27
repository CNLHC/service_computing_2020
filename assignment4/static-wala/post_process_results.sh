for i in $(ls ./results/*.dot);do
    fdp -Teps $i -Grank=LR -o ./$i.eps -Lg -LU4 -n1 -LT10
    fdp -Tsvg $i -Grank=LR -o ./$i.svg -Lg -LU4 -n1 -LT10
done

for i in $(find ./mapreduce-introduction/ -name "*.flametrace");do
    ./flamegraph.pl $i > ./out/$(basename $i).svg
done

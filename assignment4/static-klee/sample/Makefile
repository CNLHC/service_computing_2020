
	
llvm-last/test000001.ktest: service.bc
	klee service.bc


service.bc: service.cpp
	clang  -emit-llvm -c -g -O0 -Xclang -disable-O0-optnone service.cpp


.PHONY: clean

clean: 
	rm -rf klee-out*;
	rm -rf service.bc;
	rm -rf klee-last;
	




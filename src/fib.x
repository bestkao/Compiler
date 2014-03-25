 program { int x
   int fib(int n) { 
       if (n <= 1) then
          { return 1 }
       else
           { if (n == 2) then
                { return 1 }
             else
                { return fib(n-2) + fib(n-1) }
           }
   }
   int k    x = 5
   k = write(fib(read()))
   { int x
     x = 7
     x = 8
   }
 }

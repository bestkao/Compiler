program {boolean j int i
  int factorialAccumulatingParameter(int n, int result) {
      if (n < 2) then 
         { return result }
      else 
         {return factorialAccumulatingParameter(n-1,n*result) }
  }
  while (i < 1000) {
      i = write(factorialAccumulatingParameter(read(),1))
  }
  { int i
     i = 7
     i = 8
   }
}
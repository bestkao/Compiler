# Compiler for a subset of the C language

Final project for CSC 413: Software Development.

An interpreter and debugger designed from bytecodes of a subset of the C language.

## Usage Notes

To run the project from the command line type the following:

`java -jar "Interpreter.jar" <filename.x.cod>` to run the interpreter and

`java -jar "Interpreter.jar" -d <filename no extension>` to run the debugger

## Debugger commands

[break]  Set/Clear breakpoints<br>
[fct]    Display the current function<br>
[cont]   Continue execution<br>
[var]    Display variables<br>
[stpout] Step out of the current activation of a function<br>
[stpovr] Step over a line<br>
[stpin]  Step into a function<br>
[list]   List breakpoints<br>
[call]   Print callstack<br>
[trace]  Set function tracing<br>
[src]    Display the source code (with breakpoints)<br>
[q]      Quit

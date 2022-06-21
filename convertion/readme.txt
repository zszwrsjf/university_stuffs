-----------------------------------
project:propositional and predicate language convertion tool
author:Owen Zhang
date:Dec.25.2019
edition:1.0
-----------------------------------
Help to use this convertion tool
1.Please install any Java environment(JDK).
2.Please set encode type of content in UTF-8 in txt.
3.This tool can both convert FreeEnCal language(FECL) and logical formula(LF) into their opposite language.
4.You can use fec which is an exe jar to use it directly.
5.You can put your file in anywhere due to the tool can find it during the tool running by filechooser.
6.You are no need to differenciate FECL file and LF file due to the tool can do it, and you are also no need to differenciate propositional and predicate language with the same reason.
7.LF convert to FECL direction will convert into file that you can use it directly in FreeEnCal machine(Degree of '=>' is 3, InteferenceRule is only MP). 

Cautions for FECL to LF
1.You have to precondition your FECL file.
2.The FECL file will be great which is use the result of FreeEnCal machine so that making the file as original as possible.
3.The tool will detect 'AllPool' for start to convert and 'InterferenceRule' for end the convertion, which means you can write FECL file by yourself by write the FECL between AllPool and InterferenceRule.
4.the result of converting will be the LF of AllPool and NewPool.
5.This tool will detect predicate logic and show the error but not for all possible style of string.

Cautions for LF to FECL
1.You have to precondition your LF file.
2.The LF file have to start with 'LogicalFormula' right down in the first line, but you can only write 'Formula' for short due to it will only detect 'ormula'.
3.Please use '=>' to express reasoning and use capital letter between A to G and use '¡Ä', '¡Å' and '©´' for convenient. 
4.The tool allow the legal omission of brackets such as (A=>A) writing in A=>A, (©´(A¡Ä(©´A))) writing in ©´(A¡Ä©´A) due to ©´ is prior than '¡Ä' and '¡Å', '¡Ä' and '¡Å' are prior than '=>', '=>' is prior than '(' and ')'. Also, Same priorty logical symbal is in the left priorty order.
5.Enter in any line is permissed but not in the middle of LF.
6.Please make sure that your FECL file is in right order, in case the file can not get through FECL machine. 

How to run this tool
1.Open fec.jar
2.Click file button and find a file in your computer.
3.Check the reminder to make sure that the tool will do what you want to do. 
4.If the file is wrong, please fix it.
5.Click convert button and find a folder in your computer to store it in txt.
6.The file is done!
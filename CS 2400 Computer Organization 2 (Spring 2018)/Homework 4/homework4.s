* Written by Adam Schaible for CS2400 class 
* The program will mix the characters of two strings together

MAX_LEN		EQU		100
			AREA	StrToBeMixed, CODE, READONLY
				
			ENTRY 

			EXPORT main
				
main

			LDR R1, =StrOne ;string one
			LDR R2, =StrTwo ;string two
			LDR R4, =MixStr ;mixed string
			;R3 will be used as a temporary register to hold string data
			
loopBothStr
			LDRB R3, [R1], #1 ;load a byte from StrOne into R3 and increment StrOne memory address by 1 afterward
			CBZ R3, DoneStrOne ;branch if at end of string 1 (reached null in string 1) 
			STRB R3, [R4], #1 ;store string 1 byte into mixStr and then increment mixStr address by 1
			
			LDRB R3, [R2], #1 ;load a byte from StrTwo into R3 and increment StrTwo memory address by 1 afterward
			CBZ R3, DoneStrTwo ;branch if at end of string 2 (reached null in string 2)
			STRB R3, [R4], #1 ;store string 2 byte into mixStr and then increment mixStr address by 1
			
			B loopBothStr

DoneStrOne
			LDRB R3, [R2], #1 ;load a byte from StrTwo into R3 and increment StrTwo memory address by 1 afterward
			CBZ R3, DoneBoth ;branch if done mixing strings
			STRB R3, [R4], #1 ;store string 2 byte into mixStr and then increment mixStr address by 1
			
			B DoneStrOne
			
DoneStrTwo
			LDRB R3,[R1], #1 ;load a byte from StrOne into R3 and increment StrOne memory address by 1 afterward
			CBZ R3, DoneBoth ;branch if done mixing strings
			STRB R3, [R4], #1 ;store the string 1 byte into mixStr and increment mixStr address by 1
			
			B DoneStrTwo
			
DoneBoth
			STRB R3, [R4] ;Store the null terminal at the end of the mixed string
			MOV R0, #0x18 ;used to end program
			LDR R1, =0x20026 ;used to end program
			SVC #0x11 ;used to end program
			
			ALIGN
			
			AREA	StringData, DATA, READWRITE
			EXPORT adrStrOne ;Needed to display address in command
			EXPORT adrStrTwo
			EXPORT adrMixStr		

adrStrOne	DCD StrOne ;Needed to display address in command
adrStrTwo	DCD StrTwo
adrMixStr	DCD MixStr	


StrOne	DCB		"The red river mountain", 0; null terminated string
		ALIGN

StrTwo	DCB		"The forest is west", 0
		ALIGN
			
MixStr	SPACE MAX_LEN+1
		ALIGN
			
		END ;The end of the file
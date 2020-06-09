* Written by Adam Schaible for CS2400 class 
* Hamming Checker that outputs source word
MAX_LEN EQU 100

			AREA	HammingString, CODE, READONLY
			ENTRY	
			EXPORT main
main
			LDR R1, =HCode
			SUB R1, #1
			
			MOV R2, #1
			MOV R6, #0
CheckErrDet
			LDRB R5, [R1, R2]
			
			CMP R5, #0x00
			BEQ DoneErrDet
			
			CMP R5, #'0'
			BEQ IsZero
			
			EOR R6, R6, R2
IsZero
			ADD R2, #1
			B CheckErrDet
DoneErrDet
			CBZ R6, DoneErrCor
			LDRB R5, [R1, R6]
			
			CMP R5, #'0'
			BEQ NotZero
			
			MOV R5, #0
			B SkipNotZero
NotZero
			MOV R5, #'1'
SkipNotZero
			STRB R5, [R1,R6]
DoneErrCor
			LDR R3, =SrcWord
			SUB R3, #1
			
			MOV R2, #1
			MOV R4, #1
			MOV R7, #1
ErrCorLoop
			LDRB R5, [R1, R2]	
			CBZ R5, DoneExtrData
			
			CMP R2, R7
			BEQ CheckBit
			STRB R5, [R3, R4]
			ADD R4, #1
			B SkipCheckBit
CheckBit
			LSL R7, R7, #1 ; multiply R7 by 2
SkipCheckBit
			ADD R2, #1
			B ErrCorLoop
DoneExtrData
			STRB R5, [R3,R4]
			
			MOV R0, #0x18 ;for exiting program
			LDR R1, =0x20026 ;for exiting program
			SVC #0x11 ;for exiting program
			
			ALIGN
	
			AREA	HammingData, DATA, READWRITE
				
			EXPORT adrHCode
			EXPORT adrSrcWord
adrHCode 	DCD HCode
adrSrcWord	DCD SrcWord
				
HCode 		DCB "111111000001101", 0
			ALIGN
SrcWord		SPACE MAX_LEN
			ALIGN
				
			END ;The end of the file
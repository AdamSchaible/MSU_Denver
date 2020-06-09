* Written by Adam Schaible for CS2400 class 
* Convert a hex string into a decimal value

			AREA	DecimalString, CODE, READONLY
				
			ENTRY 

			EXPORT main
				
main
			MOV	R3, #0x0
			LDR R1, =HexStr
ReadMoreHex
			LDRB R0, [R1], #1 ;extract a character from the hexStr and then increment the HexStr address
			CMP R0, #0x0
			BEQ DoneReadHex ;branch if value is a null terminator value
			
			CMP R0, #'0'
			BLO DoneReadHex ;branch if R0 character is less than the digit 0, BLO (c=0)_is unsigned
			
			CMP R0, #'9'
			BHI CheckAthruF ;If R0 is greater than 9 and if it is check to see if R0 contains A thru F, BHI is unsigned greater
			
			SUB R0, #'0' ;Since	we know that RO contains a number value we extract the number(RO - ASCII'0'->RO).
			B MakeTwosComp	
			
CheckAthruF			
			CMP R0, #'A'
			BLO DoneReadHex ;If hex value is less than the letter 'A' branch to DoneReadHex, BLO means branch on unsigned less than
			CMP R0, #'F'
			BHI DoneReadHex ;If hex value is greater than the letter 'F' branch to DoneReadHex, BHI means branch if unsigned greater
			;At this point we know we are dealing with a hex letter A thru F
			SUB R0, #'A'
			ADD R0, #10 ;Gives A thru F in terms of values between 10-15

MakeTwosComp
			LSL R3, R3, #4
			ADD R3, R3, R0
			
			B ReadMoreHex


DoneReadHex
			LDR R0, =TwosComp ;puts TwosComp address in R0
			STR R3, [R0] ;Store R3 at TwosComp address
			LDR R4, =DecStr ;puts DecStr address in R4
			LDR R7, =RvsDecStr ;puts RvsDecStr address in R7
			TST R3, #0x80000000 ;sets zero flag as 1 if leading bit of R3 is 1(negative)_/TST~AND
			BEQ IsPos ;branches if R3 was positive
			
			MOV R0, #'-'
			STRB R0, [R4] ;puts a - at the beginning of the DecStr
			MVN R3, R3 ;inverts 2's comp
			ADD R3, #1
			
IsPos			
			BL DivByTen
			ADD R6, #'0'
			STRB R6, [R7], #1
			MOV R3, R5
			CMP R3, #0
			BNE IsPos
			
DoneRvsDec
			LDRB R0, [R7, #-1]!
			STRB R0, [R4], #1
			LDR R0, =RvsDecStr
			CMP R7, R0
			BHI DoneRvsDec
			
			MOV R0, #0
			STRB R0, [R4]
			
DONE
			MOV R0, #0x18 ;used to end program
			LDR R1, =0x20026 ;used to end program
			SVC #0x11 ;used to end program
			
DivByTen
			MOV R5, #0
			CMP R3, #10
			BLO DoneMinusTen
			SUB R3, #10
			ADD R5, #1
			B DivByTen
			
DoneMinusTen
			MOV R6, R3
			BX LR ;Return to main
			


			ALIGN
			
			AREA	DecimalStringData, DATA, READWRITE
			EXPORT	adrHexStr ;Needed to display address in command
			EXPORT	adrTwosComp
			EXPORT	adrDecStr
			EXPORT 	adrRvsDecStr			

adrHexStr	DCD	HexStr
adrTwosComp	DCD	TwosComp
adrDecStr	DCD DecStr
adrRvsDecStr DCD RvsDecStr	


TwosComp	DCD	0
		
HexStr		DCB	"D39", 0; null terminated string
			ALIGN		
DecStr		SPACE 12
			ALIGN
RvsDecStr	SPACE 11	
			ALIGN
	
				
		END ;The end of the file
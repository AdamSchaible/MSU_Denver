/* DFA.java */

import java.util.Set; //needed to work with the set of states and alphabet
import java.util.Map; //needed for the DFA transitions

/**
 * The DFA class
 * Based off of http://jodypaul.com/cs/theory/DFAjava/DFA.html and
 * State.java at https://gouda.msudenver.edu/moodle/mod/book/view.php?id=8937&chapterid=89
 * @author Adam Schaible, help received from Thien Ngo Lee and Krzysztof Rabka
 * @version 1.0
 */

public class DFA extends Object //Since DFA is derived from the object class
{
	//private variables renamed from http://jodypaul.com/cs/theory/DFAjava/DFA.html
	private Set<String> alphabet_Used;
	private Set<State> states_Accepted;
	private State starting_State;
	private Set<State> all_The_States;
	private Map<State, Map<String,State>> transitions;
	
	/**
	 * Constructor DFA notes
	 * 
	 * @param alphabet defines the acceptable alphabet for the DFA
	 * @param accept defines what the accept state is for this DFA
	 * @param start defines what the starting state is for this DFA
	 * @param states defines all of the available states for this DFA
	 * @param transition defines what transitions are available for this DFA
	 * 
     */
	
	public DFA(Set<State> states, Set<String> alphabet, Map<State, 
			Map<String, State>> transition, State start, Set<State> accept) 
	{
		//assign inputs to my private DFA variables during construction
		alphabet_Used = alphabet;
		states_Accepted = accept;
		starting_State = start;
		all_The_States = states;
		transitions = transition;		
	}
	
	/**
     * accepts() checks if a string is in the language
     * accepts() is used by DFATest.java
     * @return True if the string is in the language and False if the string is not in the language
     * @param test_String is the string that is being tested     * 
     */
	
	public boolean accepts(String test_String)
	{
		boolean is_The_String_Is_In_The_Language = false; //default condition
		Map<String, State> the_Transition_Of_Whatever_State_Your_On;
		State the_State_Right_Now = starting_State;
		
		//if part checks for whether a string is empty and in the language
		if (test_String.equals(""))
		{
			if (states_Accepted.contains(starting_State))
			{
				is_The_String_Is_In_The_Language = true;
			}
		}
		//else part checks for whether a string is not empty and in the language
		
		else
		{
			int counter = 0;
			
			while (counter < test_String.length())
			{
				the_State_Right_Now.increment();
				the_Transition_Of_Whatever_State_Your_On = transitions.get(the_State_Right_Now);
				the_State_Right_Now = the_Transition_Of_Whatever_State_Your_On.get(test_String.substring(counter, counter + 1));
				counter = counter + 1;
			}
			//See if the last state visited is an accept and if so mark string as accepted  
	        if (states_Accepted.contains(the_State_Right_Now)) {
	        	is_The_String_Is_In_The_Language = true;
	        }	
		}
		
		//clear the counters in the states accepted
		for (State each: states_Accepted)
		{
			each.reset();
		}
		
		return is_The_String_Is_In_The_Language; 		
	}
	
	/**
     * Returns the alphabet_Used
     * 
     * @return alphabet_Used 
     **/
    public Set<String> alphabet() 
    {
        return alphabet_Used;
    }
    
    /**
     * Retrieves the set of current states
     * 
     * @return all_The_States
     */
    public Set<State> states() 
    {
        return all_The_States;
    }
    
    /**
     * Retrieves the starting state of the DFA
     * 
     * @return starting_State
     */
    
    public State initialState() 
    {
        return starting_State;
    }
   
    /**
     * Retrieves all of states that are accepted
     * 
     * @return states_Accepted
     */
    public Set<State> acceptStates() 
    {
        return states_Accepted;
    }
    
    /**
     * Retrieves all of the transitions that are used
     * 
     * @return transitions
     */
    public Map<State, Map<String, State>> transitionFunction() 
    {
        return transitions;
    }
}

package game;

/**
 *
 * Static class that provides the level structure for the World class.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Level {

	
	private static final char[][] world1 =
        {
                {'(','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','+','R','r',' ','Z'},
                {'@',' ','Z','Y','z',' ','Y','x',' ',' ','Z',' ',' ',' ',' ',' ','x','z',' ','Y',' ','X',' ','z',' ','Z',' ',' ','R','r','X',' '},
                {'@',' ',' ','X',' ',' ','X',' ',' ',' ',' ','M','N','x','Y',' ',' ','z',' ',' ',' ',' ',' ',' ',' ',' ',' ','x','R','r','Y','X'},
                {'@',' ',' ',' ',' ',' ',' ','Z',' ',' ',' ','O','P',' ','X',' ','Z','I','J',' ','X','x','z',' ',' ',' ','A','/','R','r','z',' '},
                {'@','z',' ',' ','Z',' ',' ',' ',' ',' ',' ','a','c','5','x',' ',' ','K','L',' ','x','x','X',' ','y','X','C','D','R','r',' ',' '},
                {'@',' ',' ','!','Z','Z',' ',' ',' ','X',' ',' ','g',' ',' ',' ',' ','g',' ','X','x','Y',' ',' ',' ','3','g','$','R','r','Y',' '},
                {'@','X','x',' ',' ','z','X',' ',' ',' ',' ',' ','g','x','z',' ',' ','g',' ',' ','h','f','f','f','f','f','j',' ','R','r',' ','X'},
                {'@','Y','X','z',' ',' ','Y','x','X',' ',' ','Y','g',' ',' ',' ','X','g',' ','Z','g',' ',' ',' ',' ',' ','z','Y','R','r',' ','X'},
                {'@',' ',' ','x',' ',' ','E','F',' ',' ',' ',' ','m','f','f','f','f','l','f','f','o',' ','X','X',' ',' ','Z',' ','R','r','X','X'},
                {'@',' ','X','x',' ',' ','G','H',' ',' ',' ',' ','g',' ',' ',' ',' ',' ',' ',' ','g','Y','X',' ',' ',' ',' ',' ','R','r',' ','Z'},
                {'@',' ',' ',' ',' ',' ',' ','g','Z',' ','z',' ','g',' ',' ','Z','Y','x',' ',' ','g',' ','Z','z',' ',' ',' ',' ','R','r',' ',' '},
                {'@','Z',' ',' ',' ',' ',' ','k','f','f','f','f','o','z','x','X','x','Y',' ',' ','g',' ',' ',' ',' ',' ',' ',' ','R','r',' ',' '},
                {'@',' ','z',' ',' ',' ',' ',' ',' ',' ','X',' ','g',' ',' ','X','x',' ',' ',' ','g','z',' ',' ','X',' ',' ',' ','R','r','z',' '},
                {'@','X','X',' ',' ',' ','X',' ',' ',' ',' ',' ','g',' ',' ',' ','X','X',' ',' ','g',' ',' ','Y','x',' ','z','X','R','r',' ',' '},
                {'@','x','Y',' ',' ',' ',' ',' ',' ','z','z',' ','g',' ','X','x',' ',' ','h','f','j',' ',' ',' ',' ',' ',' ','Z','R','r',' ',' '},
                {'@','z','Y',' ','Z','x','X','V','W',' ',' ',' ','g',' ','z','x',' ',' ','g',' ','X','X',' ',' ',' ',' ',' ',' ','R','r','X',' '},
                {'@',' ',' ',' ',' ','X','x','%','#',' ',' ',' ','g',' ',' ',' ',' ',' ','g','Y',' ',' ',' ',' ',' ',' ',' ','X','R','r','Y',' '},
                {'@',' ',' ',' ',' ','z',' ','g','X','x','Z','Z','g','Z','Z',' ',' ','Z','g',' ','z',' ',' ',' ',' ','Z',' ',' ','R','r',' ','Z'},
                {'@',' ',' ',' ',' ',' ',' ','g',' ','Y','Z','h','l','i','Z',' ',' ','X','g','Z','z',' ','X',' ',' ',' ','x',' ','R','r',' ','z'},
                {'@','Z','Q','S','X','y','Y','m','f','f','f','o','p','m','f','f','f','f','j','Y',' ',' ','Z',' ',' ',' ',' ','x','R','r','Y',' '},
                {'@',' ','T','U','2',' ',' ','g',' ',' ','Z','k','n','j','Z',' ',' ',' ',' ',' ',' ','E','F',' ',' ',' ',' ',' ','R','r',' ',' '},
                {'@',' ',' ','g',' ',' ',' ','g',' ',' ','Z','Z','g','Z','Z',' ',' ','z','X',' ',' ','G','H',' ',' ',' ','X',' ','R','r',' ',' '},
                {'@','X',' ','k','f','f','f','j',' ','I','J','X','g',' ',' ','z',' ',' ','4','x',' ',' ','g',' ',' ',' ',' ','Y','R','r',' ','X'},
                {'@','Z','X',' ','x','Y','X','Y',' ','K','L',' ','g',' ',' ','Y',' ',' ',' ','x','Y',' ','g',' ',' ',' ','Z','Z','R','r',' ','x'},
                {'@','x','x','Z',' ','x','X','x','X','g',' ',' ','g',' ',' ',' ',' ',' ',' ',' ','X','Z','g','Z',' ',' ','z','6','R','r','Z',' '},
                {'@','y',' ','X',' ','X','X','z','Y','k','f','f','l','f','f','f','f','f','f','f','f','f','l','f','f','f','f','1','B','b','f','f'},
                {'@','X',' ',' ',' ','Z',' ',' ',' ','X',' ','x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','w','R','r',' ','X'},
                {'@','Z','X',' ','X','X','x','Y','x','x','Z','X',' ',' ','z','Y',' ',' ','Z','X','X',' ',' ',' ',' ',' ','x','Z','R','r',' ','Y'},
                {'@','x','X','z','Y','Y','x','X',' ',' ','z','Y','X','X','Y','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x','Y','R','r','z','Z'},
                {'@','Y','x','X',' ',' ',' ','Z','X','x',' ',' ',' ','Z','x','Y','z',' ',' ',' ',' ',' ',' ','z',' ',' ',' ','Y','R','r',' ',' '},
                {'@',' ','x','x',' ','X','Z','X',' ','Y','X','Y','X','Z','x','x','X',' ',' ',' ','z',' ',' ',' ',' ',' ',' ',' ','R','r',' ',' '},
                {'@',' ',' ','X',' ','X','z',' ',' ','Z','z','Z','X','Y',' ','X',' ','Z',' ',' ',' ','Y','Z','X',' ',' ',' ',' ','R','r',' ','X'},
                {'@','x',' ',' ','Z','x','X','Y',' ','X',' ','x','x','Z',' ',' ','X','Z','X',' ','Z','x',' ',' ','Z',' ','Y','X','R','r','X','x'},
                {'@','X','x','y','x','x','X','Z',' ','X',' ','z',' ',' ','y',' ',' ',' ','x',' ',' ','X','y',' ',' ','§','Y','x','R','r','X','x'},
                {')','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','&','R','r','x','X'},
                {'d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','d','r','X','X'}
        };
	
	/**
	 * static getter method that provides the level
	 * @return word char array
	 */
	public static char[][] getLevel(){
		return world1;
	}
	
	
}

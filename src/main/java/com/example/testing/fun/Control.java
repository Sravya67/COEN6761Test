package com.example.testing.fun;

public class Control {

    public Current current;


    public void setBoard(int n) {
        current = new Current(n);
    }


    public void commandCenter(String command) {
//        System.out.println(command);

        char action = command.toUpperCase().charAt(0);
        if (command.length() != 1 && action!= 'M' && action!= 'I' ) //R3
        {
            System.out.println("Invalid command format!" );
            return;

        }

        switch (action) {
            case 'L': //R2
                left();

                break;
            case 'R': //R2
                right();
                break;
            case 'U': //R2
                up();

                break;
            case 'D': //R2
                down();
                break;
            case 'M': // R1
                int spaces = parseSpaces(command);
                if (spaces == -1){
                    System.out.println("Invalid command format!");

                }
                else
                    move(spaces);
                break;
            case 'P': //R4
            	current.printFloor();
                break;
            case 'C':
                System.out.println(current.toString());
                break;
            case 'Q':
                System.out.println("Stopping the program...");
                System.exit(0);
                break;
            case 'I':
                int size = parseSize(command);
                if (size==-1) {
                    System.out.println("Invalid command format!");
                    break;
                 }
                else
                    init(size);
                break;
            default: //R3
                System.out.println(new IllegalArgumentException("Invalid input").toString());
                
        }

    }
    public int parseSize(String command) {
        int size = 0;
        if (command.length() < 3 ||  command.charAt(1)!=' ') {
            return -1;
        }
        try {
            size = Integer.parseInt(command.substring(2));
        } catch (NumberFormatException e) {
            return -1;
        }
        return size;
    }
    public int parseSpaces(String command) {
        int spaces = 0;
        if (command.length() < 3 ||  command.charAt(1)!=' ') {
            return -1;
        }
        try {
            spaces = Integer.parseInt(command.substring(2));
        } catch (NumberFormatException e) {
           return -1;
        }
        return spaces;
    }
    
    private void move(int spaces)  {  //R1
    	
    	    //North
    	    if(current.getRot()==0) {
    	    	if(current.getcY()+spaces>= current.getFloor().length) {
                    System.out.println(new IndexOutOfBoundsException("Out of bounds error").toString());
    	    	   }
    	    	else{
    	    		if(current.isPen()==true) {	
    	    			int[][] newfloor = current.getFloor();
    	    			for(int i=0;i<=spaces;i++) {
    	    			newfloor[current.getcX()][current.getcY()+i]=1;
    	    			}
    	    			current.setFloor(newfloor);
    	    			current.setcY(current.getcY()+spaces);	
    	    		    }
    	    		else {
    	                current.setcY(current.getcY()+spaces);
    	    	}
    	    }	
}
    	    //East
    	    else if(current.getRot()==1) {
    	    	
    	    	if(current.getcX()+spaces>=current.getFloor().length) {
                    System.out.println(new IndexOutOfBoundsException("Out of bounds error").toString());

    	    	}
    	    	else {	
    	    		if(current.isPen()) {
    	    			int[][] newfloor = current.getFloor();
    	    			for(int i=0;i<=spaces;i++) {
    	    			newfloor[current.getcX()+i][current.getcY()]=1;
    	    			}
    	    			current.setFloor(newfloor);
    	    			current.setcX(current.getcX()+spaces);	
    	    		    }
    	    		else {
    	                current.setcX(current.getcX()+spaces);
    	    	}
    	    }	
}
    	    //South
    	    else if(current.getRot()==2) {  	
    	    	if(current.getcY()-spaces<0) {
                    System.out.println(new IndexOutOfBoundsException("Out of bounds error").toString());

    	    	}
    	    	else {
	    		    if(current.isPen()) {
    	    			int[][] newfloor = current.getFloor();
    	    			for(int i=0;i<=spaces;i++) {
    	    			newfloor[current.getcX()][current.getcY()-i]=1;
    	    			}
    	    			current.setFloor(newfloor);
    	    			current.setcY(current.getcY()-spaces);	
    	    		    }
    	    		else {
    	                current.setcY(current.getcY()-spaces);
    	    	}
    	    }	
}
    	    //West
    	    else if(current.getRot()==3) {
    	    	if(current.getcX()-spaces<0) {
                    System.out.println(new IndexOutOfBoundsException("Out of bounds error").toString());

    	    	}
    	    	else {
    	    		
    	    		if(current.isPen()) {
    	    			int[][] newfloor = current.getFloor();
    	    			for(int i=0;i<=spaces;i++) {
    	    			newfloor[current.getcX()-i][current.getcY()]=1;
    	    			}
    	    			current.setFloor(newfloor);
    	    			current.setcX(current.getcX()-spaces);	
    	    		    }
    	    		else {
    	                current.setcX(current.getcX()-spaces);
    	    	}
    	    }	
}}




    private void right() { //R2

        if (current.getRot() == 0) {
            current.setRot(1);

        }
        else if (current.getRot() == 1) {
            current.setRot(2);
        }
        else if (current.getRot() == 2) {
            current.setRot(3);
        }
        else {
            current.setRot(0);
        }


    }

    private void init(int n) {
        current = new Current(n);
    }

    private void left() { //R2
        if (current.getRot() == 0) {
            current.setRot(3);
        }
        else if (current.getRot() == 1) {
            current.setRot(0);
        }
        else if (current.getRot() == 2) {
            current.setRot(1);
        }
        else {
            current.setRot(2);
        }
    }

    private void up() { //R2
        current.setPen(false);
    }

    private void down() { //R2
        current.setPen(true);
        int[][] newfloor;
        newfloor=current.getFloor();
        newfloor[current.getcX()][current.getcY()]=1;
        current.setFloor(newfloor);
    
    }


}



import java.io.RandomAccessFile;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;


public class DavisBase {


    /* This can be changed to whatever you like */
    static String prompt = "Hardiksql> ";
    static String version = "v1.0b(Final_Project)";
    static String copyright = "©2016 Chris Irwin Davis || Hardik Bhadja";
    static boolean isExit = false;
    

    /*
     * Page size for alll files is 512 bytes by default.
     * You may choose to make it user modifiable
     */
    static long pageSize = 512;
    static long ps = pageSize;
    static long pageSizec = 2048;
    static long psc = pageSizec;
    static long psColumn = pageSize;
    static long psi = pageSize;




    String tableName = null;
    RandomAccessFile meta_table;
    RandomAccessFile column_info;
    static int pk_occurence = 0;
    /*
     *  The Scanner class is used to collect user commands from the prompt
     *  There are many ways to do this. This is just one.
     *
     *  Each time the semicolon (;) delimiter is entered, the userCommand
     *  String is re-populated.
     */
    static Scanner scanner = new Scanner(System.in).useDelimiter(";");

    /** ***********************************************************************
     *  Main method
     */
    public static void main(String[] args) {
        File dataRepoP = new File("./data-repository");
        String path = "./data-repository/";
        File mi = new File(path + "meta-info");
        File tr = new File(path + "tables-repo");

        if (!dataRepoP.exists()) {
        	/* Display the welcome screen */
        	splashScreen();
        
        	System.out.println("Initialising DataBase...........");
            boolean res = false;
            try {
                dataRepoP.mkdir();
                mi.mkdir();

                RandomAccessFile meta_table = new RandomAccessFile("./data-repository/meta-info/davisbase_tables.tbl", "rw");
                meta_table.setLength(pageSize);
                RandomAccessFile column_info = new RandomAccessFile("./data-repository/meta-info/davisbase_columns.tbl", "rw");
                column_info.setLength(pageSizec);
                int l = "davisbase_tables".length();
                int c = "davisbase_columns".length();

                ps = ps - l - 1;
                meta_table.seek(ps);
                meta_table.writeByte(1);
                meta_table.writeBytes("davisbase_tables");


                ps = ps - c - 1;
                meta_table.seek(ps);
                meta_table.writeByte(2);
                meta_table.writeBytes("davisbase_columns");


                meta_table.seek(1);
                meta_table.writeByte(02);

                meta_table.seek(2);
                meta_table.writeShort(477);

                meta_table.seek(8);
                meta_table.writeShort(495);

                meta_table.seek(10);
                meta_table.writeShort(477);
                

                
                column_info.setLength(pageSizec);
                column_info.seek(8);
                column_info.writeShort((int) (pageSizec - 34));
                column_info.seek(pageSizec - 34);

                column_info.writeByte(1);
                column_info.writeByte(1);

                column_info.writeByte(16);
                column_info.writeBytes("davisbase_tables");

                column_info.writeByte(5);
                column_info.writeBytes("rowid");

                column_info.writeByte(3);
                column_info.writeBytes("INT");

                column_info.writeByte(1);
                column_info.writeByte(1);

                column_info.writeByte(2);
                column_info.writeBytes("NO");

                column_info.seek(2);
                column_info.writeShort((int) (pageSizec - 34));

                pageSizec = pageSizec - 34;
                column_info.seek(10);
                column_info.writeShort((int) (pageSizec - 40));
                column_info.seek(pageSizec - 40);

                column_info.writeByte(1);
                column_info.writeByte(2);

                column_info.writeByte(16);
                column_info.writeBytes("davisbase_tables");

                column_info.writeByte(10);
                column_info.writeBytes("table_name");

                column_info.writeByte(4);
                column_info.writeBytes("TEXT");

                column_info.writeByte(1);
                column_info.writeByte(2);

                column_info.writeByte(2);
                column_info.writeBytes("NO");

                column_info.seek(2);
                column_info.writeShort((int) (pageSizec - 40));


                pageSizec = pageSizec - 40;
                column_info.seek(12);
                column_info.writeShort((int) (pageSizec - 35));
                column_info.seek(pageSizec - 35);

                column_info.writeByte(1);
                column_info.writeByte(3);

                column_info.writeByte(17);
                column_info.writeBytes("davisbase_columns");

                column_info.writeByte(5);
                column_info.writeBytes("rowid");

                column_info.writeByte(3);
                column_info.writeBytes("INT");

                column_info.writeByte(1);
                column_info.writeByte(1);

                column_info.writeByte(2);
                column_info.writeBytes("NO");

                column_info.seek(2);
                column_info.writeShort((int) (pageSizec - 35));



                pageSizec = pageSizec - 35;
                column_info.seek(14);
                column_info.writeShort((int) (pageSizec - 41));
                column_info.seek(pageSizec - 41);

                column_info.writeByte(1);
                column_info.writeByte(4);

                column_info.writeByte(17);
                column_info.writeBytes("davisbase_columns");

                column_info.writeByte(10);
                column_info.writeBytes("table_name");

                column_info.writeByte(4);
                column_info.writeBytes("TEXT");

                column_info.writeByte(1);
                column_info.writeByte(2);

                column_info.writeByte(2);
                column_info.writeBytes("NO");

                column_info.seek(2);
                column_info.writeShort((int) (pageSizec - 41));



                pageSizec = pageSizec - 41;
                column_info.seek(16);
                column_info.writeShort((int) (pageSizec - 42));
                column_info.seek(pageSizec - 42);

                column_info.writeByte(1);
                column_info.writeByte(4);

                column_info.writeByte(17);
                column_info.writeBytes("davisbase_columns");

                column_info.writeByte(11);
                column_info.writeBytes("column_name");

                column_info.writeByte(4);
                column_info.writeBytes("TEXT");

                column_info.writeByte(1);
                column_info.writeByte(3);

                column_info.writeByte(2);
                column_info.writeBytes("NO");

                column_info.seek(2);
                column_info.writeShort((int) (pageSizec - 42));


                pageSizec = pageSizec - 42;
                column_info.seek(18);
                column_info.writeShort((int) (pageSizec - 40));
                column_info.seek(pageSizec - 40);

                column_info.writeByte(1);
                column_info.writeByte(6);

                column_info.writeByte(17);
                column_info.writeBytes("davisbase_columns");

                column_info.writeByte(9);
                column_info.writeBytes("data_type");

                column_info.writeByte(4);
                column_info.writeBytes("TEXT");

                column_info.writeByte(1);
                column_info.writeByte(4);

                column_info.writeByte(2);
                column_info.writeBytes("NO");

                column_info.seek(2);
                column_info.writeShort((int) (pageSizec - 40));



                pageSizec = pageSizec - 40;
                column_info.seek(20);
                column_info.writeShort((int) (pageSizec - 50));
                column_info.seek(pageSizec - 50);

                column_info.writeByte(1);
                column_info.writeByte(7);

                column_info.writeByte(17);
                column_info.writeBytes("davisbase_columns");

                column_info.writeByte(16);
                column_info.writeBytes("ColumnOrderition");

                column_info.writeByte(7);
                column_info.writeBytes("TINYINT");

                column_info.writeByte(1);
                column_info.writeByte(5);

                column_info.writeByte(2);
                column_info.writeBytes("NO");

                column_info.seek(2);
                column_info.writeShort((int) (pageSizec - 50));



                pageSizec = pageSizec - 50;
                column_info.seek(22);
                column_info.writeShort((int) (pageSizec - 42));
                column_info.seek(pageSizec - 42);

                column_info.writeByte(1);
                column_info.writeByte(8);

                column_info.writeByte(17);
                column_info.writeBytes("davisbase_columns");

                column_info.writeByte(11);
                column_info.writeBytes("is_nullable");

                column_info.writeByte(4);
                column_info.writeBytes("TEXT");

                column_info.writeByte(1);
                column_info.writeByte(5);

                column_info.writeByte(2);
                column_info.writeBytes("NO");

                column_info.seek(2);
                column_info.writeShort((int) (pageSizec - 42));
                tr.mkdir();
                res = true;

                column_info.seek(1);
                column_info.writeByte(8);
            } catch (Exception e) {
            	Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, e);
                System.out.println(e.getMessage());
            }
            if (res) {
                System.out.println("Database \"davisbase\" intialised...... ");
            }
        } else {
        	/* Display the welcome screen */
        	splashScreen();
            System.out.println("Database selected: davisbase");
        }
        
        /* Variable to collect user input from the prompt */
        String userCommand = "";

        while (!isExit) {
            System.out.print(prompt);
            /* toLowerCase() renders command case insensitive */
            userCommand = scanner.next().replace("\n", "").replace("\r", "").trim().toLowerCase();
            parseUserCommand(userCommand);
        }
        System.out.println("Exiting...");


    }

    public static void splashScreen() {
        System.out.println(line("-", 80));
        System.out.println("Welcome to DavisBaseLite"); 
        System.out.println("DavisBaseLite Version " + getVersion());
        System.out.println(getCopyright());
        System.out.println("\nType \"help;\" to display supported commands.");
        System.out.println(line("-", 80));
    }

    /**
     * @param s The String to be repeated
     * @param num The number of time to repeat String s.
     * @return String A String object, which is the String s appended to itself num times.
     */
    public static String line(String s, int num) {
        String a = "";
        for (int i = 0; i < num; i++) {
            a += s;
        }
        return a;
    }

    /**
     *  Help: Display supported commands
     */
    public static void help() {
        System.out.println(line("*", 80));
        System.out.println("SUPPORTED COMMANDS");
        System.out.println("All commands below are case insensitive");
        System.out.println();
        System.out.println("\tSHOW TABLES;");
        System.out.println("\tCREATE TABLE table_name (<col_name1> <col_type> primary key, <col_name2> <col_type> not null, <col_name3> <col_type>..);");
        System.out.println("\tINSERT INTO table_name (column_list) VALUES (value1,value2,value3,...);");
        System.out.println("\tUPDATE table_name SET col_name = value WHERE col_name = value;");
        System.out.println("\tDELETE FROM TABLE table_name WHERE row_id = value;");
        System.out.println("\tSELECT * FROM columns;                           Display all columns from all table of the database.");
        System.out.println("\tSELECT * FROM table_name;                        Display all records in the table.");
        System.out.println("\tSELECT * FROM table_name WHERE row_id = <value>; Display records whose rowid is <id>.");
        System.out.println("\tDROP TABLE table_name;                           Remove table data and its schema.");
        System.out.println("\tVERSION;                                         Show the program version.");
        System.out.println("\tHELP;                                            Show this help information");
        System.out.println("\tEXIT;                                            Exit the program");
        System.out.println();
        System.out.println();
        System.out.println(line("*", 80));
    }

    /** return the DavisBase version */
    public static String getVersion() {
        return version;
    }

    public static String getCopyright() {
        return copyright;
    }

    public static void displayVersion() {
        System.out.println("DavisBaseLite Version " + getVersion());
        System.out.println(getCopyright());
    }
  /*  
    
    public static void sortleafpage(RandomAccessFile file, int page){
		try{
			long length=file.length();
			int newpage=(int)(length/512+1);
			file.setLength(newpage*512);
			file.seek(512*(newpage-1));
			file.write(0x0D);
			file.write(0);
			file.writeShort(512);
			file.seek(512*(page-1)+1);
			int num=file.readByte();
			for(int i=1;i<=num;i++){
				moveLeafRow(i,file,page,newpage);
			}
			file.seek(512*(page-1));
			file.write(0x0D);
			file.write(0);
			file.writeShort(512);
			for(int i=1;i<=num;i++){
				moveLeafRow(i,file,newpage,page);
			}
			file.setLength((newpage-1)*512);
		}
		catch (Exception e) {
			System.out.println("Unable to sort the page");
			System.out.println(e);
		}
	}
    
    public static void moveLeafRow(int rownum,RandomAccessFile file, int oldpage, int newpage){
		try{
			file.seek(512*(oldpage-1)+2+2*rownum);
			int offset1=file.readShort();
			file.seek(offset1+512*(oldpage-1));
			int payload=file.readShort();
			file.seek(offset1+6+512*(oldpage-1));
			int attributes=file.read();
			int size=payload+attributes+3;
			file.seek(512*(newpage-1)+1);
			int num=file.read();
			file.seek(512*(newpage-1)+1);
			file.write(num+1);
			file.seek(512*(newpage-1)+2);
			int offset2=file.readShort();
			offset2=offset2-size;
			file.seek(512*(newpage-1)+2);
			file.writeShort(offset2);
			file.seek(512*(newpage-1)+4+2*num);
			file.writeShort(offset2);
			for(int i=0;i<size;i++){
				file.seek(offset1+i+512*(oldpage-1));
				Byte value=file.readByte();
				file.seek(offset2+i+512*(newpage-1));
				file.writeByte(value);				
			}
		}
		catch (Exception e) {
			System.out.println("Unable to move row to the new leaf page");
			System.out.println(e);
		}
	}
    
    public static int findparent(RandomAccessFile file, int page, int rootpage){
		try{
			file.seek(512*(page-1));
			byte type=file.readByte();
			file.read();
			int offset=file.readShort();
			int rowid=-1;
			if(type==0x0D){
				file.seek(offset+2+512*(page-1));
				rowid=file.readInt();
			}
			else{
				file.seek(offset+4+512*(page-1));
				rowid=file.readInt();
			}
			int no=0;
			int pagenum=0;
			file.seek(512*(rootpage-1)+1);
			int num=file.read();
			for(int i=1;i<=num;i++){
				file.seek(512*(rootpage-1)+2*i+6);
				offset=file.readShort();
				file.seek(offset+4+512*(rootpage-1));
				int key=file.readInt();
				if(rowid<key){
					no=i;
					file.seek(offset+512*(rootpage-1));
					pagenum=file.readInt();
					if(pagenum==page)
						return rootpage;
					else
						break;
				}
				
			}
			if(no==0){
				file.seek(512*(rootpage-1)+4);
				pagenum=file.readInt();
				if(pagenum==page)
					return rootpage;
				else
					return findparent(file,page,pagenum);
			}
			else{
				return findparent(file,page,pagenum);
			}
		}
		catch (Exception e) {
			System.out.println("Unable to find parent");
			System.out.println(e);
			return -1;
		}
	}
    
    public static int findpage(RandomAccessFile file,int rowid,int page){
		try{
			file.seek(512*(page-1));
			byte type=file.readByte();
			if(type==0x0D){
				return page;
			}
			else{
				file.seek(512*(page-1)+1);
				int num=file.readByte();
				int no=-1;
				for(int i=1;i<=num;i++){
					file.seek(512*(page-1)+2*i+6);									
					int offset=file.readShort();
					file.seek(offset+4+512*(page-1));
					int key=file.readInt();
					if(key>rowid){
						no=i;	
						break;
					}
				}
				if(no!=-1){
					file.seek(512*(page-1)+2*no+6);
					int offset=file.readShort();
					file.seek(offset+512*(page-1));
					int pagenum=file.readInt();
					int a=findpage(file,rowid,pagenum);
					return a;
				}
				else{
					file.seek(512*(page-1)+4);
					int pagenum=file.readInt();
					int a=findpage(file,rowid,pagenum);
					return a;
				}
			}
		}
		catch (Exception e) {
			System.out.println("Unable to find page");
			System.out.println(e);
			return -1;
		}
	}
    
    public static int findNoInLeaf(RandomAccessFile file,int rowid,int page){
		try{
			file.seek(512*(page-1)+1);
			int no=-1;
			int num=file.readByte();
			for(int i=1;i<=num;i++){
				file.seek(512*(page-1)+2+2*i);
				int offset=file.readShort();
				file.seek(offset+2+512*(page-1));
				int key=file.readInt();
				if(key==rowid)
					no=i;
			}
			return no;
		}
		catch (Exception e) {
			System.out.println("Unable to find no in leaf");
			System.out.println(e);
			return -1;
		}
	}
    
   */ 
    public static void parseUserCommand (String userCommand) {

        /* commandTokens is an array of Strings that contains one token per array element
         * The first token can be used to determine the type of command
         * The other tokens can be used to pass relevant parameters to each command-specific
         * method inside each case statement */
        
        ArrayList<String> commandTokens = new ArrayList<String>(Arrays.asList(userCommand.split(" ")));


        /*
        *  This switch handles a very small list of hardcoded commands of known syntax.
        *  You will want to rewrite this method to interpret more complex commands.
        */
        switch (commandTokens.get(0)) {
        case "select":
            if (commandTokens.get(3).equalsIgnoreCase("columns"))
                parseColumnSelectQuery(userCommand);
            else
                parseSelectQuery(userCommand);
            break;
        case "show":
            parseShowQuery(userCommand);
            break;
        case "insert":
            parseInsertQuery(userCommand);
            break;
        case "drop":
            dropTable(userCommand);
            break;
        case "delete":
            dropTable(userCommand);
            break;
        case "create":
            parseCreateQuery(userCommand);
            break;
        case "update":
            parseUpdateQuery(userCommand);
            break;
        case "help":
            help();
            break;
        case "version":
            displayVersion();
            break;
        case "exit":
            isExit = true;
            break;
        case "quit":
            isExit = true;
        default:
            System.out.println("Command not recognised : \"" + userCommand + "\"");
            System.out.println("Try \"help;\" for more information.");
            break;
        }
    }

    public static void parseColumnSelectQuery(String userCommand) {
        ArrayList<Character> SelectDisplayRowData = new ArrayList<>();
        try {
            RandomAccessFile binaryQueFile = new RandomAccessFile("./data-repository/meta-info/davisbase_columns.tbl", "rw");
            int StratAdd = 0x08;
            binaryQueFile.seek(StratAdd);
            int temp1 = binaryQueFile.readShort();
            while (temp1 != 0) {
                binaryQueFile.seek(StratAdd);
                temp1 = binaryQueFile.readShort();
                StratAdd += 2;
                binaryQueFile.seek(++temp1);
                int row_id = binaryQueFile.read();
                int seek_rlength = temp1 + 1;
                binaryQueFile.seek(seek_rlength);
                int first_length = binaryQueFile.read();
                for (int i = 0; i < first_length; i++) {
                    binaryQueFile.seek(++seek_rlength);
                    char CharVal = (char)(binaryQueFile.read());
                    SelectDisplayRowData.add(CharVal);
                }
                int srlength = seek_rlength + 1;
                SelectDisplayRowData.add(' ');
                for (int k = 2; k < 6; k++) {
                    binaryQueFile.seek(srlength);
                    int rlength = binaryQueFile.read();
                    for (int i = 0; i < rlength; i++) {
                        srlength += 1;
                        binaryQueFile.seek(srlength);
                        char CharVal = (char)(binaryQueFile.read());
                        SelectDisplayRowData.add(CharVal);
                    }
                    SelectDisplayRowData.add(' ');
                    srlength += 1;
                    rlength = binaryQueFile.read();
                }
                SelectDisplayRowData.add(';');
                binaryQueFile.seek(StratAdd - 2);
                temp1 = binaryQueFile.read();
            }
            String final_data = "";
            String column_data = "";
            String column_wise[] = null;
            for (int j = 0; j < SelectDisplayRowData.size(); j++) {
                final_data = final_data + SelectDisplayRowData.get(j);
            }
            String rowdata[] = final_data.split(";");
            String[] key1;
            ArrayList<String> colss = new ArrayList<>();
            for (int k = 0; k < rowdata.length - 1; k++) {
                key1 = rowdata[k].split(" ");
                for (int jo = 0; jo < key1.length; jo++) {
                    colss.add(key1[jo]);
                }
            }
            int try3 = 0;
            int try4 = 1;
            ArrayList<Integer> yug = new ArrayList<>();
            ArrayList<String> yug1 = new ArrayList<>();
            String day = null;
            for (int tre1 = 0; tre1 < colss.size(); tre1 += 5) {
                day = colss.get(tre1);
                if (!yug1.contains(day))
                    yug1.add(day);
            }
            for (int j = 0; j < yug1.size(); j++) {
                int count = 0;
                for (int i = 0; i < colss.size(); i += 5) {
                    if ((colss.get(i).equalsIgnoreCase(yug1.get(j)))) {
                        count++;
                        yug.add(count);
                    }
                }
            }
            int ray1 = 3;
            for (int ray = 0; ray < colss.size() / 5; ray++) {
                colss.set(ray1, yug.get(ray).toString());
                ray1 += 5;
            }
            ArrayList<String> headings = new ArrayList<String>() ;
            headings.add("row_id");
            headings.add("table_name");
            headings.add("column_name");
            headings.add("data_type");
            headings.add("ColumnOrderition");
            headings.add("is_nullable");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < headings.size(); i++) {
                System.out.format("%-20s", headings.get(i));
            }
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
            for (int try1 = 0; try1 < colss.size() / 5; try1++) {
                System.out.format("%-20s", try4);
                for (int try2 = try3; try2 < try3 + 5; try2++) {
                    System.out.format("%-20s", colss.get(try2));
                }
                System.out.println();
                try4++;
                try3 += 5;
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
        	Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, e);
        }

    }


    static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

    /**
     *  Stub method for dropping tables
     *  @param dropTableString is a String of the user input
     */
    public static void dropTable(String dropTableString) {
        try {
            ArrayList<String> listOfQueryStringTokens = new ArrayList<String>(Arrays.asList(dropTableString.split(" ")));
            int rowno = 0;
            String tableName = null;
            if (listOfQueryStringTokens.size() > 3) {
                tableName = listOfQueryStringTokens.get(3);
                rowno = Integer.parseInt(listOfQueryStringTokens.get(7));
            } else {
                tableName = listOfQueryStringTokens.get(2);
            }

            RandomAccessFile binaryDataFile2 = new RandomAccessFile("./data-repository/tables-repo/" + tableName + "/" + tableName + ".tbl", "rw");
            if (listOfQueryStringTokens.get(0).equalsIgnoreCase("delete")) {
            	//System.out.println("if");
                int startAdd, result;
                int matchIndex = 0;
                int matchId = 0;
                int startAddress = 0x08;
                binaryDataFile2.seek(startAddress);
                while (binaryDataFile2.readShort() != 0 ) {
                    binaryDataFile2.seek(startAddress);
                    if (-1 == binaryDataFile2.readShort()) {
                    } else {
                        binaryDataFile2.seek(startAddress);
                        result = (int) binaryDataFile2.readShort();
                        result += 4;
                        binaryDataFile2.seek(result);
                        int recValue = binaryDataFile2.read();
                        if (recValue == rowno) {
                            matchIndex = recValue;
                            matchId = result - 4;
                            startAdd = 8;
                            while (binaryDataFile2.readShort() != 0) {
                                binaryDataFile2.seek(startAdd);
                                result = binaryDataFile2.readShort();
                                if (result == matchId) {
                                   // System.out.println("Record Deleted");
                                    binaryDataFile2.seek(startAddress);
                                    binaryDataFile2.writeShort(0xFFFF);
                                    break;
                                }
                                startAdd += 2;
                            }
                        }
                    }
                    startAddress += 2;
                }
            } else if (listOfQueryStringTokens.get(0).equalsIgnoreCase("drop")) {
            	//System.out.println("else if");
                String a = "./data-repository/tables-repo/" + tableName;
                //System.out.println("a"+a);
                File s = new File(a);
                deleteDir(s);
                RandomAccessFile binaryDataFile = new RandomAccessFile("./data-repository/meta-info/davisbase_tables.tbl", "rw");
                int startAdd = 0x08;
                int next = 6;
                int lengthVal;
                int selectValue, nValue;
                binaryDataFile.seek(startAdd);
                
               /* while ( binaryDataFile.readShort() != 0 ) {
                    System.out.println(binaryDataFile.readShort());
                }*/
                //System.out.println("binaryDataFile.readShort()"+binaryDataFile);
                while (binaryDataFile.readShort() != 0) {
                    String str = "";
                    binaryDataFile.seek(startAdd);
                    selectValue = binaryDataFile.readShort();
                   // System.out.println("selectValue"+selectValue);
                    if (selectValue == -1) {
                    } else {
                        selectValue++;
                        binaryDataFile.seek(next);
                        nValue = binaryDataFile.readShort();
                        if (nValue == 0) {
                            nValue = 512;
                        }
                        lengthVal = nValue - selectValue;
                        binaryDataFile.seek(selectValue);
                        ArrayList<Character> listOfCharRowData = new ArrayList<>();
                        for (int i = 0; i < lengthVal; i++) {
                            binaryDataFile.seek(selectValue++);
                            char charValue = (char)(binaryDataFile.read());
                            listOfCharRowData.add(charValue);
                        }
                        for (int j = 0; j < listOfCharRowData.size(); j++) {
                            str = str + listOfCharRowData.get(j);
                        }
                      //  System.out.println("str"+str);
                        //System.out.println("tableName"+tableName);
                        if (str.equals(tableName)) {
                            binaryDataFile.seek(startAdd);
                            binaryDataFile.writeShort(0xFFFF);
                            System.out.println("Table " + tableName + " has been deleted." );
                            break;
                        }
                    }
                    //System.out.println("startAdd"+startAdd);
                    //System.out.println("next"+next);
                    startAdd += 2;
                    next += 2;
                    binaryDataFile.seek(startAdd);
                    //System.out.println(binaryDataFile.seek(startAdd));
                }
            }
        } catch (Exception ex) {
        	System.out.println("Table Doesn't Exist");
            System.out.println(ex);
            Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    /**
     *  Stub method for executing queries
     *  @param queryString is a String of the user input
     */
    private static void parseUpdateQuery(String updateString) {
        try {
            ArrayList<String> listOfUpdateStringTokens = new ArrayList<String>(Arrays.asList(updateString.split(" ")));
            String tbl = listOfUpdateStringTokens.get(1);
            String updateColumnName= listOfUpdateStringTokens.get(3);
            String updateColumnValue = listOfUpdateStringTokens.get(5);
            String criteriaCoulmnName = listOfUpdateStringTokens.get(7);
            String criteriaCoulmnValue = listOfUpdateStringTokens.get(9);
            ArrayList<String> listOfAttributeOrder = new ArrayList<>();
            ArrayList<String> listOfColName = new ArrayList<String>();
            File tableMetaFolder = new File("./data-repository/tables-repo/" + tbl);
            File[] fileNames = tableMetaFolder.listFiles();
            //System.out.println("fileNames"+fileNames);
            for (int i = 0; i < fileNames.length; i++) {
                if (fileNames[i].isFile()) {
                    String columnNdxName = fileNames[i].getName();
                    if (columnNdxName.contains(".ndx")) {
                        listOfColName.add(columnNdxName.substring(0, columnNdxName.indexOf(".")));
                    }
                }
            }
            //System.out.println("listOfColName"+listOfColName);
            for (int o = 0; o < listOfColName.size() + 15; o++) {
                listOfAttributeOrder.add(".");
            }
            //System.out.println("listOfAttributeOrder"+listOfAttributeOrder);
            for (int g = 0; g < listOfColName.size(); g++) {
                RandomAccessFile colNameFile = new RandomAccessFile("./data-repository/tables-repo/" + tbl + "/" + listOfColName.get(g) + ".ndx", "rw");
                colNameFile.seek(2);
                int position = colNameFile.read();
                listOfAttributeOrder.set(position, listOfColName.get(g));
            }
            int arrOfAttributeIndex = listOfAttributeOrder.indexOf(criteriaCoulmnName);
            //System.out.println("arrOfAttributeIndex"+arrOfAttributeIndex);
            int var = listOfAttributeOrder.indexOf(updateColumnName);
            //System.out.println("var"+var);
            RandomAccessFile binaryDataFile = new RandomAccessFile("./data-repository/tables-repo/" + tbl + "/" + tbl + ".tbl", "rw");
            ArrayList<Character> listOfCharRowData = new ArrayList<>();
            int startAddress = 8;
            binaryDataFile.seek(startAddress);
            int readShortValue = binaryDataFile.readShort();
            //System.out.println("readShortValue"+readShortValue);
            while (readShortValue != 0) {
                if (-1 == readShortValue) {
                    startAddress = startAddress + 2; 
                    binaryDataFile.seek(startAddress);
                    readShortValue = binaryDataFile.readShort();
                } else {
                    binaryDataFile.seek(startAddress);
                    readShortValue = binaryDataFile.readShort();
                    binaryDataFile.seek(readShortValue);
                    binaryDataFile.seek(readShortValue + 5);
                    int numOfCol = binaryDataFile.read();
                    //System.out.println("numOfCol"+numOfCol);
                    int seekRowLength1 = readShortValue + 5 + numOfCol + 1;
                    //System.out.println("seekRowLength1"+seekRowLength1);
                    binaryDataFile.seek(seekRowLength1);
                    int firstLength1 = binaryDataFile.read();
                    //System.out.println("firstLength1"+firstLength1);
                    for (int i = 0; i < firstLength1; i++) {
                        binaryDataFile.seek(seekRowLength1 + 1);
                        char charValue1 = (char)(binaryDataFile.read());
                       // System.out.println(charValue1);
                        listOfCharRowData.add(charValue1);
                    }
                    int strLength = seekRowLength1 + firstLength1 + 1;
                    //System.out.println("strLength"+strLength);
                    listOfCharRowData.add('`');
                    for (int k = 1; k < numOfCol; k++) {
                        binaryDataFile.seek(strLength);
                        int rlength1 = binaryDataFile.read();
                        //System.out.println("rlength1"+rlength1);
                        for (int i = 0; i < rlength1; i++) {
                            strLength += 1;
                            binaryDataFile.seek(strLength);
                            char charValue1 = (char)(binaryDataFile.read());
                            listOfCharRowData.add(charValue1);
                        }
                        strLength += 1;
                        listOfCharRowData.add('`');
                    }
                    startAddress = startAddress + 2;
                    //System.out.println("startAddress"+startAddress);
                    listOfCharRowData.add(';');
                    binaryDataFile.seek(startAddress);
                }
            }
            String finalData1 = "";
            for (int j = 0; j < listOfCharRowData.size(); j++) {
                finalData1 = finalData1 + listOfCharRowData.get(j);
            }
            //System.out.println("finalData1"+finalData1);
            ArrayList<String> listOfStrRowData = new ArrayList<String>(Arrays.asList(finalData1.split(";")));
            ArrayList<String> listOfColumns = new ArrayList<>();
            for (int k = 0; k < listOfStrRowData.size() - 1; k++) {
                String extractedCol[] = listOfStrRowData.get(k).split("`");
                String strValAttributeWise = extractedCol[arrOfAttributeIndex];
                listOfColumns.add(strValAttributeWise);
            }
            //System.out.println("listOfColumns"+listOfColumns);
            ArrayList<Integer> listOfRecNo = new ArrayList<>();
            int var1 = 1;
            for (int io = 0; io < listOfColumns.size(); io++) {
                if (listOfColumns.get(io).equals(criteriaCoulmnValue)) {
                    listOfRecNo.add(var1);
                }
                var1++;
            }
            //System.out.println("listOfRecNo"+listOfRecNo);
            int toUpdate = 0;
            for (int y = 0; y < listOfColumns.size(); y++) {
                if (listOfColumns.get(y).equalsIgnoreCase(criteriaCoulmnValue))
                    toUpdate = y;
            }
            ArrayList<String> listOfCoulmnAttributeWise = new ArrayList<>();
            for (int k = 0; k < listOfStrRowData.size() - 1; k++) {
                String extractedCol[] = listOfStrRowData.get(k).split("`");
                String strValAttributeWise = extractedCol[var];
                listOfCoulmnAttributeWise.add(strValAttributeWise);
            }
            //System.out.println("listOfCoulmnAttributeWise"+listOfCoulmnAttributeWise);
            listOfCoulmnAttributeWise.set(toUpdate, updateColumnValue);
            for (int t = 0; t < listOfRecNo.size(); t++) {
                startAddress = 0x08;
                int result = 0;
                binaryDataFile.seek(startAddress);
                int tem2 = binaryDataFile.readShort();
                while (tem2 != 0) {
                    if (-1 == tem2) {
                        startAddress = startAddress + 2; 
                        binaryDataFile.seek(startAddress);
                        tem2 = binaryDataFile.readShort();
                    } else {
                        binaryDataFile.seek(startAddress);
                        result = binaryDataFile.readShort();
                        result += 4;
                        binaryDataFile.seek(result);
                        int recValue = binaryDataFile.read();
                        int c = 1;
                        if (recValue == listOfRecNo.get(t)) {
                            binaryDataFile.seek(result + 1);
                            int nCol = binaryDataFile.read();
                            int dist = result + 1 + nCol + 1;
                            binaryDataFile.seek(dist);
                            int rlength = binaryDataFile.read();
                            while (c != var + 1) {
                                binaryDataFile.seek(dist);
                                rlength = binaryDataFile.read();
                                for (int u = 0; u < rlength + 1; u++) {
                                    dist++;
                                }
                                c++;
                            }
                            binaryDataFile.seek(dist + 1);
                            binaryDataFile.writeBytes(updateColumnValue);
                            System.out.println("1 Row updated");
                        }
                        startAddress += 2;
                        binaryDataFile.seek(startAddress);
                    }
                }
            }
        } catch (Exception ex) {
            //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    public static void parseSelectQuery(String queryString) {
        try {
            ArrayList<String> QueryTokenL = new ArrayList<String>(Arrays.asList(queryString.split(" ")));
            String ControllingCoulmnName = null;
            String comp_val = null;
            String tbl_nm = QueryTokenL.get(3);
            if (QueryTokenL.size() > 4) {
                ControllingCoulmnName = QueryTokenL.get(5);
                comp_val = QueryTokenL.get(7);
            }
            File TableMetaFolder = new File("./data-repository/tables-repo/" + tbl_nm);
            File[] fileNames = TableMetaFolder.listFiles();
            ArrayList<String> ColNameList = new ArrayList<String>();
            for (int i = 0; i < fileNames.length; i++) {
                if (fileNames[i].isFile()) {
                    String ColumnNdxName = fileNames[i].getName();
                    if (ColumnNdxName.contains(".ndx")) {
                        ColNameList.add(ColumnNdxName.substring(0, ColumnNdxName.indexOf(".")));
                    }
                }
            }
            ArrayList<String> AttributeOrder = new ArrayList<>();
            for (int o = 0; o < ColNameList.size() + 15; o++) {
                AttributeOrder.add(".");
            }

            for (int g = 0; g < ColNameList.size(); g++) {
                RandomAccessFile nmc = new RandomAccessFile("./data-repository/tables-repo/" + tbl_nm + "/" + ColNameList.get(g) + ".ndx", "rw");
                nmc.seek(2);
                int position = nmc.read();
                AttributeOrder.set(position, ColNameList.get(g));
            }
            ArrayList<Integer> match_id = new ArrayList<>();
            ArrayList<Integer> match_index = new ArrayList<>();
            
            if (QueryTokenL.size() <= 4) {
            	
                if (QueryTokenL.get(1).equals("*")) {
                    ArrayList<Character> SelectDisplayRowData = new ArrayList<>();
                    RandomAccessFile binaryQueFile = new RandomAccessFile("./data-repository/tables-repo/" + tbl_nm + "/" + tbl_nm + ".tbl", "rw");
                    int StratAdd = 8;
                    binaryQueFile.seek(StratAdd);
                    int a = 0;
                    int temp1 = binaryQueFile.readShort();
                    while (temp1 != 0) {
                        if (-1 == temp1) {
                            StratAdd = StratAdd + 2; 
                            binaryQueFile.seek(StratAdd);
                            temp1 = binaryQueFile.readShort();
                        }
                        else {
                            binaryQueFile.seek(StratAdd);
                            temp1 = binaryQueFile.readShort();
                            String h = Integer.toHexString(temp1);
                            binaryQueFile.seek(temp1);
                            String data = (Integer.toString(binaryQueFile.read()));
                            binaryQueFile.seek(temp1 + 5);
                            int ncol = binaryQueFile.read();
                            int seek_rlength = temp1 + 5 + ncol + 1;
                            binaryQueFile.seek(seek_rlength);
                            int first_length = binaryQueFile.read();
                            for (int i = 0; i < first_length; i++) {
                                a++;
                                binaryQueFile.seek(seek_rlength + 1);
                                char CharVal = (char)(binaryQueFile.read());
                                SelectDisplayRowData.add(CharVal);
                            }
                            int srlength = seek_rlength + first_length + 1;
                            SelectDisplayRowData.add('\t');
                            SelectDisplayRowData.add('\t');
                            for (int k = 1; k < ncol; k++) {
                            binaryQueFile.seek(srlength);
                            int rlength = binaryQueFile.read();
                            for (int i = 0; i < rlength; i++) {
                            	srlength += 1;
                            	binaryQueFile.seek(srlength);
                            	char CharVal = (char)(binaryQueFile.read());
                            	SelectDisplayRowData.add(CharVal);
                            }
                            SelectDisplayRowData.add('\t');
                            srlength += 1;
                            SelectDisplayRowData.add('\t');
                            }
                            StratAdd = StratAdd + 2;
                            SelectDisplayRowData.add(';');
                            binaryQueFile.seek(StratAdd);
                        }
                    }
                    int y = 0;
                    for (int i = 0; i < ColNameList.size(); i++) {
                        System.out.print(AttributeOrder.get(i));
                        System.out.print("\t\t");
                        y++;
                    }
                    int lines = y * 16;
                    System.out.println();
                    System.out.println(line("-", lines));
                    String final_data = "";
                    for (int j = 0; j < SelectDisplayRowData.size(); j++) {
                        final_data = final_data + SelectDisplayRowData.get(j);
                    }
                    String rowdata[] = final_data.split(";");
                    for (int k = 0; k < rowdata.length - 1; k++) {
                        System.out.println(rowdata[k]);
                    }
                    System.out.println(line("-", lines));
                } 
                else {
                    RandomAccessFile binaryQueFile = new RandomAccessFile("./data-repository/tables-repo/" + tbl_nm + "/" + tbl_nm + ".tbl", "rw");
                    ArrayList<Character> SelectDispRowData = new ArrayList<>();
                    int StratAddress = 8;
                    binaryQueFile.seek(StratAddress);
                    int readValue = binaryQueFile.readShort();
                    while (readValue != 0) {
                        if (-1 == readValue) {
                            StratAddress = StratAddress + 2; 
                            binaryQueFile.seek(StratAddress);
                            readValue = binaryQueFile.readShort();
                        } else {
                            binaryQueFile.seek(StratAddress);
                            readValue = binaryQueFile.readShort();
                            binaryQueFile.seek(readValue);
                            binaryQueFile.seek(readValue + 5);
                            int NumOfCol = binaryQueFile.read();
                            int seek_rlength1 = readValue + 5 + NumOfCol + 1;
                            binaryQueFile.seek(seek_rlength1);
                            int first_length1 = binaryQueFile.read();
                            for (int i = 0; i < first_length1; i++) {
                                binaryQueFile.seek(seek_rlength1 + 1);
                                char CharVal1 = (char)(binaryQueFile.read());
                                SelectDispRowData.add(CharVal1);
                            }
                            int StrLength = seek_rlength1 + first_length1 + 1;
                            SelectDispRowData.add('`');
                            for (int k = 1; k < NumOfCol; k++) {
                                binaryQueFile.seek(StrLength);
                                int rlength1 = binaryQueFile.read();
                                for (int i = 0; i < rlength1; i++) {
                                    StrLength += 1;
                                    binaryQueFile.seek(StrLength);
                                    char CharVal1 = (char)(binaryQueFile.read());
                                    SelectDispRowData.add(CharVal1);
                                }
                                StrLength += 1;
                                SelectDispRowData.add('`');
                            }
                            StratAddress = StratAddress + 2;
                            SelectDispRowData.add(';');
                            binaryQueFile.seek(StratAddress);
                        }
                    }
                    String[] SelectAttributeList = QueryTokenL.get(1).split(",");
                    int[] SelectAttributeIndex = new int[SelectAttributeList.length];
                    for(int i =0;i<SelectAttributeList.length;i++){
                    	SelectAttributeIndex[i] = AttributeOrder.indexOf(SelectAttributeList[i]);
                    }
                    String final_data1 = "";
                    for (int j = 0; j < SelectDispRowData.size(); j++) {
                        final_data1 = final_data1 + SelectDispRowData.get(j);
                    }
                    ArrayList<String> RowDataList = new ArrayList<String>(Arrays.asList(final_data1.split(";")));
                    ArrayList<ArrayList<String>> SelectColumn = new ArrayList<>();
                    ArrayList<String> SelectDataAttributeWise = new ArrayList<>();
                    for (int k = 0; k <RowDataList.size() - 1; k++) {
                    	String column_extract[] = RowDataList.get(k).split("`");
                    	for(int l = 0; l <SelectAttributeList.length; l++){
                    		SelectDataAttributeWise.add(column_extract[SelectAttributeIndex[l]]);
                    	}
                    	SelectColumn.add(SelectDataAttributeWise);
                    }
                    for (int y = 0; y < SelectAttributeList.length; y++) {
                    	System.out.print(SelectAttributeList[y]+"\t");
                    }
                    System.out.println();
                    System.out.println(line("-", 16));
                    for (int n = 0; n < RowDataList.size() - 1; n++) {
                            String[] dispOP = RowDataList.get(n).split("`");
                            for(int i =0;i<SelectAttributeList.length;i++){
                            	System.out.print(dispOP[SelectAttributeIndex[i]]+"\t");
                            }
                            System.out.println();
                    }
                    System.out.println(line("-", 16));
                }
            }
            else if (QueryTokenL.contains("where")) {
                if (ControllingCoulmnName.equals("rowid") || ControllingCoulmnName.equals("row_id")) {
                    int fg = 0;
                    int row_no = Integer.parseInt(QueryTokenL.get(7));
                    String operator=QueryTokenL.get(6);
                    if (operator.equals("=")) {
                        fg = 1;
                    } else if (operator.equals("<")) {
                        fg = 2;
                    } else if (operator.equals(">")) {
                        fg = 3;
                    } else {
                        fg = 0;
                    }
                    RandomAccessFile binaryQueFile = new RandomAccessFile("./data-repository/tables-repo/" + tbl_nm + "/" + tbl_nm + ".tbl", "rw");
                    int StratAddress, result;
                    
                    switch (fg) {
                    case 0:
                        System.out.println(operator+" Operator not supported");
                        break;
                    case 1:
                        binaryQueFile.seek(0x10);
                        StratAddress = 0x08;
                        result = 0;
                        binaryQueFile.seek(StratAddress);
                        while (binaryQueFile.readShort() != 0) {
                            binaryQueFile.seek(StratAddress);
                            result = binaryQueFile.readShort();
                            result += 4;
                            binaryQueFile.seek(result);
                            int rec_val = binaryQueFile.read();
                            
                            if (rec_val == row_no) {
                                match_index.add(row_no);
                                match_id.add(result);
                            }
                            StratAddress += 2;
                        }
                        break;
                    case 2:
                        binaryQueFile.seek(0x10);
                        StratAddress = 0x08;
                        result = 0;
                        binaryQueFile.seek(StratAddress);
                        while (binaryQueFile.readShort() != 0) {
                            binaryQueFile.seek(StratAddress);
                            result = binaryQueFile.readShort();
                            result += 4;
                            binaryQueFile.seek(result);
                            int rec_val = binaryQueFile.read();
                            if (rec_val < row_no) {
                                match_index.add(row_no);
                                match_id.add(result);
                            }
                            StratAddress += 2;
                        }
                        break;
                    case 3:
                        binaryQueFile.seek(0x10);
                        StratAddress = 0x08;
                        result = 0;
                        binaryQueFile.seek(StratAddress);
                        while (binaryQueFile.readShort() != 0) {
                            binaryQueFile.seek(StratAddress);
                            result = binaryQueFile.readShort();
                            result += 4;
                            binaryQueFile.seek(result);
                            int rec_val = binaryQueFile.read();
                            if (rec_val > row_no) {
                                match_index.add(row_no);
                                match_id.add(result);
                            }
                            StratAddress += 2;
                        }
                        break;
                    }

                    ArrayList<Character> SelectDisplayRowData1 = new ArrayList<>();
                    RandomAccessFile binaryQueFile1 = new RandomAccessFile("./data-repository/tables-repo/" + tbl_nm + "/" + tbl_nm + ".tbl", "rw");
                    for (int r = 0; r < match_id.size(); r++) {
                        binaryQueFile.seek(match_id.get(r));

                        String data = (Integer.toString(binaryQueFile.read()));
                        int readValue = match_id.get(r);
                        binaryQueFile1.seek(readValue + 1);
                        int ncol = binaryQueFile1.read();
                        
                        int seek_rlength = readValue + 1 + ncol + 1;
                        binaryQueFile1.seek(seek_rlength);

                        int first_length = binaryQueFile1.read();
                        
                        for (int i = 0; i < first_length; i++) {

                            binaryQueFile1.seek(seek_rlength + 1);
                            char CharVal = (char)(binaryQueFile1.read());
                            SelectDisplayRowData1.add(CharVal);
                        }
                        int srlength = seek_rlength + first_length + 1;
                        SelectDisplayRowData1.add('\t');
                        SelectDisplayRowData1.add('\t');
                        for (int k = 1; k < ncol; k++) {
                            binaryQueFile1.seek(srlength);
                            int rlength = binaryQueFile1.readByte();
                            for (int i = 0; i < rlength; i++) {
                                srlength += 1;
                                binaryQueFile1.seek(srlength);
                                char CharVal = (char)(binaryQueFile1.read());
                                SelectDisplayRowData1.add(CharVal);
                            }
                            SelectDisplayRowData1.add('\t');
                            srlength += 1;
                            SelectDisplayRowData1.add('\t');
                        }
                        SelectDisplayRowData1.add(';');
                    }
                    int dashN = 0;
                    for (int i = 0; i < ColNameList.size(); i++) {
                        System.out.print(AttributeOrder.get(i));
                        System.out.print("\t\t");
                        dashN++;
                    }
                    int lines = dashN * 16;
                    System.out.println();
                    System.out.println(line("-", lines));
                    
                    String final_data = "";
                    for (int j = 0; j < SelectDisplayRowData1.size(); j++) {
                        final_data = final_data + SelectDisplayRowData1.get(j);
                    }
                    String rowdata[] = final_data.split(";");
                    for (int k = 0; k < rowdata.length; k++) {
                        System.out.println(rowdata[k]);
                    }
                    System.out.println(line("-", lines));
                }
                else if ((ColNameList.contains(ControllingCoulmnName))) {
                    ArrayList<Character> SelectDisplayRowData = new ArrayList<>();
                    RandomAccessFile binaryQueFile = new RandomAccessFile("./data-repository/tables-repo/" + tbl_nm + "/" + tbl_nm + ".tbl", "rw");
                    int StratAdd = 8;
                    binaryQueFile.seek(StratAdd);
                    int a = 0;
                    int temp1 = binaryQueFile.readShort();
                    while (temp1 != 0) {
                        if (-1 == temp1) {
                            StratAdd = StratAdd + 2; 
                            binaryQueFile.seek(StratAdd);
                            temp1 = binaryQueFile.readShort();
                        } else {
                            binaryQueFile.seek(StratAdd);
                            temp1 = binaryQueFile.readShort();
                            String h = Integer.toHexString(temp1);
                            binaryQueFile.seek(temp1);
                            String data = (Integer.toString(binaryQueFile.read()));
                            binaryQueFile.seek(temp1 + 5);
                            int ncol = binaryQueFile.read();
                            int seek_rlength = temp1 + 5 + ncol + 1;
                            binaryQueFile.seek(seek_rlength);
                            int first_length = binaryQueFile.read();
                            for (int i = 0; i < first_length; i++) {
                                a++;
                                binaryQueFile.seek(seek_rlength + 1);
                                char CharVal = (char)(binaryQueFile.read());
                                SelectDisplayRowData.add(CharVal);
                            }
                            int srlength = seek_rlength + first_length + 1;
                            SelectDisplayRowData.add('\t');
                            SelectDisplayRowData.add('\t');
                            for (int k = 1; k < ncol; k++) {
                                binaryQueFile.seek(srlength);
                                int rlength = binaryQueFile.read();
                                for (int i = 0; i < rlength; i++) {
                                    srlength += 1;
                                    binaryQueFile.seek(srlength);
                                    char CharVal = (char)(binaryQueFile.read());
                                    SelectDisplayRowData.add(CharVal);
                                }
                                SelectDisplayRowData.add('\t');
                                srlength += 1;
                                SelectDisplayRowData.add('\t');
                            }
                            StratAdd = StratAdd + 2;
                            SelectDisplayRowData.add(';');
                            binaryQueFile.seek(StratAdd);
                        }
                    }
                    ArrayList<Character> SelectDispRowData = new ArrayList<>();
                    int StratAddress = 8;
                    binaryQueFile.seek(StratAddress);
                    int readValue = binaryQueFile.readShort();
                    while (readValue != 0) {
                        if (-1 == temp1) {
                            StratAdd = StratAdd + 2; 
                            binaryQueFile.seek(StratAdd);
                            temp1 = binaryQueFile.readShort();
                        } else {
                            binaryQueFile.seek(StratAddress);
                            readValue = binaryQueFile.readShort();
                            String h1 = Integer.toHexString(readValue);
                            binaryQueFile.seek(readValue);
                            String data1 = (Integer.toString(binaryQueFile.read()));
                            binaryQueFile.seek(readValue + 5);
                            int NumOfCol = binaryQueFile.read();
                            int seek_rlength1 = readValue + 5 + NumOfCol + 1;
                            binaryQueFile.seek(seek_rlength1);
                            int first_length1 = binaryQueFile.read();
                            for (int i = 0; i < first_length1; i++) {
                                binaryQueFile.seek(seek_rlength1 + 1);
                                char CharVal1 = (char)(binaryQueFile.read());
                                SelectDispRowData.add(CharVal1);
                            }
                            int StrLength = seek_rlength1 + first_length1 + 1;
                            SelectDispRowData.add('`');
                            for (int k = 1; k < NumOfCol; k++) {
                                binaryQueFile.seek(StrLength);
                                int rlength1 = binaryQueFile.read();
                                for (int i = 0; i < rlength1; i++) {
                                    StrLength += 1;
                                    binaryQueFile.seek(StrLength);
                                    char CharVal1 = (char)(binaryQueFile.read());
                                    SelectDispRowData.add(CharVal1);
                                }
                                StrLength += 1;
                                SelectDispRowData.add('`');
                            }
                            StratAddress = StratAddress + 2;
                            SelectDispRowData.add(';');
                            binaryQueFile.seek(StratAddress);
                        }
                    }
                    String final_data1 = "";
                    for (int j = 0; j < SelectDispRowData.size(); j++) {
                        final_data1 = final_data1 + SelectDispRowData.get(j);
                    }
                    ArrayList<String> RowDataList = new ArrayList<String>(Arrays.asList(final_data1.split(";")));
                    int index_of_query_element = AttributeOrder.indexOf(ControllingCoulmnName);
                    ArrayList<Integer> colNameList = new ArrayList<>();
                    ArrayList<String> SelectColumn = new ArrayList<>();
                    for (int k = 0; k < RowDataList.size() - 1; k++) {
                    	String column_extract[] = RowDataList.get(k).split("`");
                        String SelectDataAttributeWise = column_extract[index_of_query_element];
                        SelectColumn.add(SelectDataAttributeWise);
                    }
                    int red = 0;
                    int fg = 0;
                    String operator=QueryTokenL.get(6);
                    if (operator.equals("=")) {
                        fg = 1;
                    } else if (operator.equals("<")) {
                        fg = 2;
                    } else if (operator.equals(">")) {
                        fg = 3;
                    } else {
                        fg = 0;
                    }

                    if (fg == 1) {
                        for (String cv : SelectColumn) {
                            if (cv.equalsIgnoreCase(comp_val)) {
                                colNameList.add(red);
                            }
                            red++;
                        }
                    } else if (fg == 2) {
                        for (String cv : SelectColumn) {
                            if (Integer.parseInt(cv) < Integer.parseInt(comp_val)) {
                                colNameList.add(red);
                            }
                            red++;
                        }
                    } else if (fg == 3) {
                        for (String cv : SelectColumn) {
                            if (Integer.parseInt(cv) > Integer.parseInt(comp_val)) {
                                colNameList.add(red);
                            }
                            red++;
                        }
                    }
                    if (QueryTokenL.get(1).equals("*")) {
                    	for (int i = 0; i < ColNameList.size(); i++) {
	                        System.out.print(AttributeOrder.get(i));
	                        System.out.print("\t\t");
	                    }
                    	System.out.println();
                        System.out.println(line("-", 32));
                        for (int n = 0; n < colNameList.size(); n++) {
                            System.out.println(RowDataList.get(colNameList.get(n)).replaceAll("`", "\t\t"));
                        }
                        System.out.println(line("-", 32));
                    } else {
                        String[] QueryColumName = QueryTokenL.get(1).split(",");
                        int[] q = new int[QueryColumName.length];
                        for (int y = 0; y < QueryColumName.length; y++) 
                        	q[y]= AttributeOrder.indexOf(QueryColumName[y]);
                        
                        ArrayList<ArrayList<String>> SelectCoulmnAttributeWise = new ArrayList<>();
                        ArrayList<String> SelectDataAttributeWise = new ArrayList<>();
                        for (int k = 0; k <RowDataList.size() - 1; k++) {
                        	String column_extract[] = RowDataList.get(k).split("`");
                        	for(int l = 0; l <QueryColumName.length; l++){
                        		SelectDataAttributeWise.add(column_extract[q[l]]);
                        	}
                        	SelectCoulmnAttributeWise.add(SelectDataAttributeWise);
                        }
                        
                        for (int y = 0; y < QueryColumName.length; y++) {
                        	System.out.print(QueryColumName[y]+"\t");
                        }
                        System.out.println();
                        System.out.println(line("-", 16));
                        for (int n = 0; n < RowDataList.size() - 1; n++) {
                                String[] dispOP = RowDataList.get(colNameList.get(n)).split("`");
                                for(int i =0;i<QueryColumName.length;i++){
                                	System.out.print(dispOP[q[i]]+"\t");
                                }
                                System.out.println();
                        }
                        System.out.println(line("-", 16));    
                    }
                } else {
                    System.out.println("Column " + ControllingCoulmnName + " not found in " + tbl_nm + " table");
                }
            } else {
                System.out.println("Syntax error....");
            }
        } catch (Exception ex) {
            //Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Error: "+ex.getMessage()+": Cannot Display Rows");
        }
    }

    /**
     *  Stub method for creating new tables
     *  @param queryString is a String of the user input
     */
    public static void parseCreateQuery(String createQueryString) {
        ArrayList<String> listOfColumnName = new ArrayList<>();
        ArrayList<String> listOfCreateQueryToken = new ArrayList<String>(Arrays.asList(createQueryString.split(" ")));
        int frq;
        frq = Collections.frequency(listOfCreateQueryToken, "primary");
        //System.out.println("f"+frq);
        String tableName = listOfCreateQueryToken.get(2);
        String temp[] = tableName.replace("(", " ").split(" ");
        String tableFileName = temp[0] + ".tbl";
        DavisBase d = new DavisBase();
        try {
            File f = new File("./data-repository/tables-repo/" + temp[0]);
            boolean e = f.exists();
            String dTypeString;

            String queryColName;
            createQueryString = createQueryString.substring(createQueryString.indexOf("(") + 1, createQueryString.length() - 1);
            int sizeOfRecord = 0;
            int codeInSerial = 0;
            String datatype[] = createQueryString.split(",");
            int columnOrder1 = 0;
            if (!e) {
                if (frq <= 1) {
                    Boolean flagDType = false;
                    for (int i = 0; i < datatype.length; i++) {
                        dTypeString = datatype[i];
                        String dType[] = dTypeString.split(" ");
                        queryColName = datatype[i].substring(0, datatype[i].indexOf(" "));
                        listOfColumnName.add(queryColName);
                        flagDType = d.ValidateDataType(dType[1]);
                        if (flagDType == false)
                            break;
                        String flagPrim = null;

                        if (dType[1].equalsIgnoreCase("int")) {
                            sizeOfRecord = sizeOfRecord + 4;
                            codeInSerial = 0x06;
                        } else if (dType[1].equalsIgnoreCase("tinyint")) {
                            sizeOfRecord = sizeOfRecord + 1;
                            codeInSerial = 0x04;
                        } else if (dType[1].equalsIgnoreCase("smallint")) {
                            sizeOfRecord = sizeOfRecord + 2;
                            codeInSerial = 0x05;
                        } else if (dType[1].equalsIgnoreCase("bigint")) {
                            sizeOfRecord = sizeOfRecord + 8;
                            codeInSerial = 0x07;
                        } else if (dType[1].equalsIgnoreCase("real")) {
                            sizeOfRecord = sizeOfRecord + 4;
                            codeInSerial = 0x08;
                        } else if (dType[1].equalsIgnoreCase("double")) {
                            sizeOfRecord = sizeOfRecord + 8;
                            codeInSerial = 0x09;
                        } else if (dType[1].equalsIgnoreCase("datetime")) {
                            sizeOfRecord = sizeOfRecord + 8;
                            codeInSerial = 0x0A;
                        } else if (dType[1].equalsIgnoreCase("date")) {
                            sizeOfRecord = sizeOfRecord + 8;
                            codeInSerial = 0x0B;
                        } else if (dType[1].equalsIgnoreCase("text")) {
                            codeInSerial = 0x0C;
                        }
                        int columnOrder = 0;
                        int dataTypeLength = dType[1].length();
                        int columnLength = queryColName.length();
                        int tableLength = temp[0].length();
                        int isnullLength = 0;
                        if (dTypeString.toLowerCase().contains("primary key") || dTypeString.toLowerCase().contains("[not null]") ) {
                            if (dTypeString.toLowerCase().contains("primary key")) {
                                isnullLength = 3;
                                flagPrim = "PRI";
                            } else {
                                isnullLength = 2;
                                flagPrim = "NO";
                            }
                        } else {
                            isnullLength = 3;
                            flagPrim = "YES";
                        }
                        int totalSize = 1 + tableLength + columnLength + dataTypeLength + 1 + isnullLength + 6;
                        if (flagDType && frq <= 1) {
                            f.mkdir();
                            RandomAccessFile columnFile = new RandomAccessFile("./data-repository/tables-repo/" + temp[0] + "/" + queryColName + ".ndx", "rw");
                            columnFile.setLength(pageSize);
                            columnFile.seek(0);
                            columnFile.writeByte(codeInSerial);
                            int nullValue;
                            if (flagPrim.equalsIgnoreCase("yes")) {
                                nullValue = 1;
                            } else {
                                nullValue = 0;
                            }
                            columnFile.writeByte(nullValue);
                            columnFile.write(columnOrder);
                            columnOrder++;
                            columnFile.writeByte(0x00);
                            columnFile.writeByte(0x10);
                            RandomAccessFile davisBaseColFile = new RandomAccessFile("./data-repository/meta-info/davisbase_columns.tbl", "rw");
                            davisBaseColFile.setLength(2048);
                            davisBaseColFile.seek(1);
                            int columnNumber = davisBaseColFile.read();
                            davisBaseColFile.seek(2);
                            int charValue1 = davisBaseColFile.readShort();
                            davisBaseColFile.seek(2);
                            davisBaseColFile.writeShort((int) (charValue1 - totalSize));
                            davisBaseColFile.seek(charValue1 - totalSize);
                            columnNumber++;
                            davisBaseColFile.writeByte(1);
                            davisBaseColFile.writeByte(columnNumber);
                            davisBaseColFile.writeByte(tableLength);
                            davisBaseColFile.writeBytes(temp[0]);
                            davisBaseColFile.writeByte(columnLength);
                            davisBaseColFile.writeBytes(queryColName);
                            davisBaseColFile.writeByte(dataTypeLength);
                            davisBaseColFile.writeBytes(dType[1]);
                            davisBaseColFile.writeByte(1);
                            columnOrder++;
                            davisBaseColFile.writeByte(columnOrder);
                            davisBaseColFile.writeByte(isnullLength);
                            davisBaseColFile.writeBytes(flagPrim);
                            davisBaseColFile.seek(1);
                            davisBaseColFile.writeByte(columnNumber);
                            davisBaseColFile.seek(2);
                            davisBaseColFile.writeShort((int) (charValue1 - totalSize));
                            davisBaseColFile.seek(8 + columnNumber * 2 - 2);
                            davisBaseColFile.writeShort(charValue1 - totalSize);
                        }
                    }
                    if (flagDType && frq <= 1) {
                        f.mkdir();
                        RandomAccessFile davisBaseTableFile = new RandomAccessFile("./data-repository/meta-info/davisbase_tables.tbl", "rw");
                        davisBaseTableFile.seek(1);
                        int recordCount = davisBaseTableFile.read();
                        davisBaseTableFile.seek(2);
                        ps = davisBaseTableFile.readShort();
                        davisBaseTableFile.setLength(pageSize);
                        ps = ps - temp[0].length() - 1;
                        davisBaseTableFile.seek(ps);
                        recordCount = recordCount + 1;
                        davisBaseTableFile.writeByte(recordCount);
                        davisBaseTableFile.writeBytes(temp[0]);
                        davisBaseTableFile.seek(1);
                        davisBaseTableFile.writeByte(recordCount);
                        davisBaseTableFile.seek(2);
                        davisBaseTableFile.writeShort((int)(ps));
                        davisBaseTableFile.seek(8 + recordCount * 2 - 2);
                        davisBaseTableFile.writeShort((int) ps);
                        RandomAccessFile tableRepoFiles = new RandomAccessFile("./data-repository/tables-repo/" + temp[0] + "/" + temp[0] + ".tbl", "rw");
                        tableRepoFiles.setLength(pageSize);
                        tableRepoFiles.seek(0);
                        tableRepoFiles.write(0x0d);
                        tableRepoFiles.seek(1);
                        int colRecCount = tableRepoFiles.readByte();
                        int ColHeaderSize = 0;
                        if (colRecCount == 0) {
                            psColumn = 512;
                            for (int c = 0; c < listOfColumnName.size(); c++) {
                                ColHeaderSize += listOfColumnName.get(c).length();
                            }
                            tableRepoFiles.seek(psColumn - ColHeaderSize - listOfColumnName.size());

                            for (int w = 0; w < listOfColumnName.size(); w++) {
                                tableRepoFiles.writeByte(listOfColumnName.get(w).length());
                                tableRepoFiles.writeBytes(listOfColumnName.get(w));
                            }
                            psColumn = psColumn - ColHeaderSize - listOfColumnName.size();
                            tableRepoFiles.seek(2);
                            tableRepoFiles.writeShort((int)psColumn);
                        } else {
                        }
                    }
                } else {
                    System.out.println("A table cannot contain more than one primary keys.....");
                }
            } else {
                System.out.println("File " + temp[0] + " already exists");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void parseShowQuery(String queryString) {
        try {
            System.out.println("-----------");
            System.out.println("Table Names");
            System.out.println("-----------");
            File file = new File("./data-repository/tables-repo");
            String[] tableNames = file.list();
            for (String name : tableNames) {
                if (new File("./data-repository/tables-repo/" + name).isDirectory()) {
                    System.out.println(name);
                }
            }
            System.out.println("davisbase_columns");
            System.out.println("davisbase_tables");
            System.out.println("-----------");
        } catch (Exception e) {
        	Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    

    public boolean ValidateDataType(String type) {
        try {
            switch (type) {
            case "int":
            case "smallint":
            case "tinyint":
            case "bigint":
            case "null":
            case "text":
            case "real":
            case "double":
            case "datetime":
            case "date":
                return true;
            default:
                throw new Exception("invalid Datatype!!!");

            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static void parseInsertQuery(String insertTableQueryString) {

        ArrayList<String> listOfInsertQueryToken = new ArrayList<String>(Arrays.asList(insertTableQueryString.split(" ")));
        String tableName = listOfInsertQueryToken.get(4);
        int p = 0;
        int isPrimKey = 0;

        File tableMetaFolder = new File("./data-repository/tables-repo/" + tableName);
        if (tableMetaFolder.exists()) {
            String insertColList = listOfInsertQueryToken.get(3);
            String insertColVal = listOfInsertQueryToken.get(6);
            insertColList = insertColList.substring(insertColList.indexOf("(") + 1, insertColList.length() - 1);
            ArrayList<String> listOfColsInsert = new ArrayList<>();
            String stringArrOfInsertCol[] = insertColList.split(",");
            for (int y = 0; y < stringArrOfInsertCol.length; y++) {
                listOfColsInsert.add(stringArrOfInsertCol[y]);
            }

            insertColVal = insertColVal.substring(insertColVal.indexOf("(") + 1, insertColVal.length() - 1);
            String stringArrOfInsertColValue[] = insertColVal.split(",");

            ArrayList<String> listOfInsertColValue = new ArrayList<>();

            for (int y = 0; y < stringArrOfInsertColValue.length; y++) {
                listOfInsertColValue.add(stringArrOfInsertColValue[y]);
            }
            ArrayList<String> listofLeftColInsert = new ArrayList<>();
            Boolean length = true;
            int diff = stringArrOfInsertCol.length - stringArrOfInsertColValue.length;
            if (stringArrOfInsertCol.length < stringArrOfInsertColValue.length) {
                length = false;
            } else {
                for (int l = stringArrOfInsertCol.length; l > (stringArrOfInsertCol.length - diff); l--) {
                    String remaining = stringArrOfInsertCol[l - 1];
                    listofLeftColInsert.add(remaining);
                }
            }

            ArrayList<String> listOfColName = new ArrayList<String>();
            File[] fileNames = tableMetaFolder.listFiles();
            int flag = 0;
            int nullFlag = 0;

            ArrayList<Integer> t = new ArrayList<>();
            ArrayList<Integer> t1 = new ArrayList<>();
            int cRecordSize = 0;
            ArrayList<String> listOfTemp = new ArrayList<>();
            for (int i = 0; i < fileNames.length; i++) {
                if (fileNames[i].isFile()) {
                    String columnNdxName = fileNames[i].getName();
                    if (columnNdxName.contains(".ndx")) {
                        listOfColName.add(columnNdxName.substring(0, columnNdxName.indexOf(".")));
                    }
                }
            }
            listOfTemp = listOfColName;
            for (int m = 0; m < listOfColsInsert.size(); m++) {
                if (listOfColName.contains(listOfColsInsert.get(m))) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }

            for (int j = 0; j < listOfColsInsert.size(); j++) {
                if (listOfTemp.contains(listOfColsInsert.get(j))) {
                    listOfTemp.remove(listOfColsInsert.get(j));
                }
            }
            for (int r = 0; r < listOfTemp.size(); r++) {
                listofLeftColInsert.add(listOfTemp.get(r));
            }
            for (int h = 0; h < listofLeftColInsert.size(); h++) {
                try {
                    RandomAccessFile colFile = new RandomAccessFile("./data-repository/tables-repo/" + tableName + "/" + listofLeftColInsert.get(h) + ".ndx", "rw");
                    colFile.seek(0);
                    p = colFile.read();
                    t.add(p);
                    colFile.seek(1);
                    isPrimKey = colFile.read();
                    t1.add(isPrimKey);
                } catch (Exception ex) {
                	Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (t1.contains(0)) {
                nullFlag = 0;
            } else {
                nullFlag = 1;
            }
            ArrayList<Integer> listOfNullType = new ArrayList<>();
            if (flag == 1 && length) {
                if (nullFlag == 1) {
                    for (int y = 0; y < t.size(); y++) {
                        if (t.get(y) == 0x04) {
                            listOfNullType.add(1);
                        } else if (t.get(y) == 0x05) {
                            listOfNullType.add(2);
                        } else if (t.get(y) == 0x06 || t.get(y) == 0x08) {
                            listOfNullType.add(4);
                        } else if (t.get(y) == 0x09 || t.get(y) == 0x0A || t.get(y) == 0x0B) {
                            listOfNullType.add(8);
                        } else {
                            listOfNullType.add(0);
                        }
                    }

                    for (int g = 0; g < stringArrOfInsertCol.length; g++) {
                        try {
                            RandomAccessFile colsGivenFile = new RandomAccessFile("./data-repository/tables-repo/" + tableName + "/" + stringArrOfInsertCol[g] + ".ndx", "rw");
                            colsGivenFile.seek(0);
                            int dataType = colsGivenFile.read();
                            if (dataType == 0x04) {
                                cRecordSize += 1;
                            } else if (dataType == 0x05) {
                                cRecordSize += 2;
                            } else if (dataType == 0x06) {
                                cRecordSize += 4;
                            } else if (dataType == 0x07) {
                                cRecordSize += 8;
                            } else if (dataType == 0x08) {
                                cRecordSize += 4;
                            } else if (dataType == 0x09) {
                                cRecordSize += 8;
                            } else if (dataType == 0x0A) {
                                cRecordSize += 8;
                            } else if (dataType == 0x0B) {
                                cRecordSize += 8;
                            } else if (dataType == 0x0C) {

                                cRecordSize += stringArrOfInsertColValue[g].length();
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    listOfColName.clear();
                    for (int i = 0; i < fileNames.length; i++) {
                        if (fileNames[i].isFile()) {
                            String columnNdxName = fileNames[i].getName();
                            if (columnNdxName.contains(".ndx")) {
                                listOfColName.add(columnNdxName.substring(0, columnNdxName.indexOf(".")));
                            }

                        }
                    }
                    ArrayList<String> listOfOrderValues = new ArrayList<>();
                    int nullByte = 0;
                    for (int o = 0; o < listOfColName.size() + 15; o++) {
                        listOfOrderValues.add(".");
                    }
                    ArrayList<String> listOfOrderValuesType = new ArrayList<>();

                    for (int o = 0; o < listOfColName.size() + 15; o++) {
                        listOfOrderValuesType.add(".");
                    }
                    
                    if (diff >= 0) {
                        for (int u = 0; u < listofLeftColInsert.size(); u++) {
                            try {
                                RandomAccessFile colTypeFile = new RandomAccessFile("./data-repository/tables-repo/" + tableName + "/" + listofLeftColInsert.get(u) + ".ndx", "rw");
                                colTypeFile.seek(2);
                                int a = colTypeFile.read();
                                colTypeFile.seek(0);
                                int dtype = colTypeFile.read();
                                if (dtype == 0x04) {
                                    listOfOrderValues.set(a, "~");
                                    nullByte += 1;
                                } else if (dtype == 0x05) {
                                    nullByte += 2;
                                    listOfOrderValues.set(a, "~");
                                } else if (dtype == 0x06 || dtype == 0x08) {
                                    nullByte += 4;
                                    listOfOrderValues.set(a, "~");
                                } else if (dtype == 0x09 || dtype == 0x0A || dtype == 0x0B) {
                                    nullByte += 8;
                                    listOfOrderValues.set(a, "~");
                                } else {
                                    nullByte += 0;
                                    listOfOrderValues.set(a, "~");
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    int bytesNo = 2 + 4 + (2 * listOfColName.size()) + 1 + cRecordSize + nullByte;
                    for (int a = 0; a < listOfInsertColValue.size(); a++) {
                        try {
                            RandomAccessFile colsOrdFile = new RandomAccessFile("./data-repository/tables-repo/" + tableName + "/" + listOfColsInsert.get(a) + ".ndx", "rw");
                            colsOrdFile.seek(2);
                            int o = colsOrdFile.read();
                            listOfOrderValues.set(o, stringArrOfInsertColValue[a]);
                    //        System.out.println(listOfOrderValues);
                        } catch (Exception ex) {
                            Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        RandomAccessFile insertTableFile = new RandomAccessFile("./data-repository/tables-repo/" + tableName + "/" + tableName + ".tbl", "rw");
                        insertTableFile.setLength(pageSize);
                        insertTableFile.seek(1);
                        int row_id = insertTableFile.read();
                        row_id++;
                        insertTableFile.seek(1);
                        insertTableFile.write(row_id);
                        insertTableFile.seek(2);
                        psi = insertTableFile.readShort();
                        psi = psi - bytesNo;
                        insertTableFile.seek(2);
                        insertTableFile.writeShort((int)psi);
                        insertTableFile.seek(psi);
                        insertTableFile.writeByte(cRecordSize);
                        insertTableFile.writeInt(row_id);
                        int count = 0;
                        for (int y = 0; y < listOfColName.size(); y++) {
                            count++;
                        }
                        insertTableFile.write(count);
                        for (int d = 0; d < listOfColName.size(); d++) {
                            RandomAccessFile deleteColsFile = new RandomAccessFile("./data-repository/tables-repo/" + tableName + "/" + listOfColName.get(d) + ".ndx", "rw");
                            deleteColsFile.seek(0);
                            int dt = deleteColsFile.read();

                            deleteColsFile.seek(2);
                            int op = deleteColsFile.read();
                            listOfOrderValuesType.set(op, Integer.toString(dt));
                            
                        }
                        for (int o = 0; o < listOfOrderValuesType.size(); o++) {
                            if (listOfOrderValuesType.get(o).equalsIgnoreCase(".")) {

                            } else {
                                insertTableFile.writeByte(Integer.parseInt(listOfOrderValuesType.get(o)));
                            }
                        }
                        for (int o = 0; o < listOfOrderValues.size(); o++) {
                            if (listOfOrderValues.get(o).equalsIgnoreCase(".")) {

                            } else {
                                insertTableFile.writeByte(listOfOrderValues.get(o).length());
                                insertTableFile.writeBytes(listOfOrderValues.get(o));
                            }
                        }
                        insertTableFile.seek(8 + 2 * row_id - 2);
                        insertTableFile.writeShort((int)psi);

                        for (int i = 0; i < listOfColName.size(); i++) {
                            try {
                                RandomAccessFile recordColFile = new RandomAccessFile("./data-repository/tables-repo/" + tableName + "/" + listOfColName.get(i) + ".ndx", "rw");
                                recordColFile.seek(3);
                                int x = recordColFile.readShort();
                                recordColFile.seek(2);
                                int ordp = recordColFile.read();
                                recordColFile.seek(x);
                                recordColFile.writeByte(row_id);
                                recordColFile.writeBytes(listOfOrderValues.get(ordp));
                                x += 16;
                                recordColFile.seek(3);
                                recordColFile.writeShort(x);
                            } catch (Exception ex) {
                                Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println("Row Inserted");
                    } catch (Exception ex) {
                        Logger.getLogger(DavisBase.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Null Values are not allowed");
                }
            } else {
                System.out.println("Column does not exist!!!!!");
            }
        }
        else {
            System.out.println("Please create the table " + tableName + " before inserting values");
        }
    }
}
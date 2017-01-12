//Nathan Wheeler
import java.io.*;
import java.util.*;
import java.math.*;
import java.util.ArrayList;

class stringInt{
int index;
String key;
int probeLength;

public stringInt(String k, int p){
key = k;
probeLength = p;}

public int getProbeLength(){
return probeLength;
}
public String getKey(){
return key;
}
public void setKey(String str){
key = str;
}
public void setProbeLength(int pl){
probeLength = pl;
}
}

public class n00871989{

static int lpTotalInsertions = 0;
static int lptotalInsertPL = 0;
static double lpInsertAvgPl = 0;
static int lpSuccessfulFinds = 0;
static int lpSuccessfulFindsPL = 0;
static double lpFindSuccessAvgPl = 0;
static int lpFailedFinds = 0;
static int lpFailedFindsPL = 0;
static double lpFindFailAvgPl = 0;
static int lpSuccessfulDeletes = 0;
static int lpSuccessfulDeletesPL = 0;
static double lpDeleteSuccessAvgPl = 0;
static int lpFailedDeletes = 0;
static int lpFailedDeletesPL = 0;
static double lpDeleteFailAvgPl = 0;


static int qpTotalInsertions = 0;
static int qptotalInsertPL = 0;
static double qpInsertAvgPl = 0;
static int qpSuccessfulFinds = 0;
static int qpSuccessfulFindsPL = 0;
static double qpFindSuccessAvgPl = 0;
static int qpFailedFinds = 0;
static int qpFailedFindsPL = 0;
static double qpFindFailAvgPl = 0;
static int qpSuccessfulDeletes = 0;
static int qpSuccessfulDeletesPL = 0;
static double qpDeleteSuccessAvgPl = 0;
static int qpFailedDeletes = 0;
static int qpFailedDeletesPL = 0;
static double qpDeleteFailAvgPl = 0;



public static int hashFunc3(String key, int arraySize, int stringLength)
{
double i = 0;
double hashVal = 0;
for(int j= stringLength; j>0; j--) // left to right
{
int letter = (int) key.charAt(j-1); // get char code
hashVal = (hashVal + (Math.pow(26,i) * letter)) % arraySize; // mod
//System.out.println(hashVal);
i++;
}
return (int)hashVal; // no mod
} // end hashFunc3()
/////////////////////////////////////////////////////////////////////////////////////////////////Linear Insert function

public static void insertLP(String key, stringInt[] array){

int probeCount = 0;

int hashValue = hashFunc3(key, array.length, key.length());
probeCount++;//initial prob++

while(array[hashValue].getKey() != null && !array[hashValue].getKey().equals(" ")){
++hashValue;
hashValue %=array.length;
probeCount++;
} 
array[hashValue].setKey(key); //inserts item
lpTotalInsertions++;
lptotalInsertPL = lptotalInsertPL + probeCount;
array[hashValue].setProbeLength(probeCount);
}
//////////////////////////////////////////////////////////////////////////////////////////////////Linear Insert Display

/////////////////////////////////////////////////////////////////////////////////////////////////Quadratic Insert

public static void insertQP(String key, stringInt[] array){

int probeCount = 0;

int hashValue = hashFunc3(key, array.length, key.length());
int temp = hashValue;
probeCount++;//initial prob++

while(array[temp].getKey() != null && !array[temp].getKey().equals(" ")){

temp = hashValue + (int)Math.pow(probeCount,2);

temp %= array.length;
probeCount++;
} 
array[temp].setKey(key); //inserts item
qpTotalInsertions++;
qptotalInsertPL = qptotalInsertPL + probeCount;
array[temp].setProbeLength(probeCount);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////Linear Find
public static void findLP(String key, stringInt[] array){
int probeCount = 0; 

int hashValue = hashFunc3(key, array.length, key.length());
probeCount++;//initial prob++

while(array[hashValue].getKey() != null){
if(array[hashValue].getKey().equals(key)){
lpSuccessfulFinds++;
lpSuccessfulFindsPL+= probeCount;
System.out.printf("%-40s %-7s %-8s %-12d %-12s %n", array[hashValue].getKey(), "yes", "", probeCount, "");
return;
}// end if
++hashValue;             //check next index
hashValue %=array.length;
probeCount++;
} //end while
System.out.printf("%-40s %-7s %-8s %-12s %-12d %n", key, "", "yes", "", probeCount);
lpFailedFinds++;         //if the key is null, 
lpFailedFindsPL +=probeCount;

}// end findLP
//////////////////////////////////////////////////////////////////////////////////////////////////////////////Quadratic Find

public static void findQP(String key, stringInt[] array){
int probeCount = 0; 


int hashValue = hashFunc3(key, array.length, key.length());
int temp = hashValue;
probeCount++;//initial prob++

while(array[temp].getKey() != null){
if(array[temp].getKey().equals(key)){
qpSuccessfulFinds++;
qpSuccessfulFindsPL+= probeCount;
System.out.printf("%-40s %-7s %-8s %-12d %-12s %n", array[temp].getKey(), "yes", "", probeCount, "");
return;
}// end if
temp = hashValue + (int)Math.pow(probeCount,2);            //check next index
temp %=array.length;
probeCount++;
} //end while
System.out.printf("%-40s %-7s %-8s %-12s %-12d %n", key, "", "yes", "", probeCount);
qpFailedFinds++;         //if the key is null, 
qpFailedFindsPL +=probeCount;

}// end findLP
////////////////////////////////////////////////////////////////////////////////////////////////Delete LP
public static void deleteLP(String key, stringInt[] array){
int probeCount = 0; 

int hashValue = hashFunc3(key, array.length, key.length());
probeCount++;//initial prob++

while(array[hashValue].getKey() != null){
if(array[hashValue].getKey().equals(key)){
lpSuccessfulDeletes++;
lpSuccessfulDeletesPL+= probeCount;
System.out.printf("%-40s %-7s %-8s %-12d %-12s %n", array[hashValue].getKey(), "yes", "", probeCount, "");
array[hashValue].setKey(" ");
return;
}// end if
++hashValue;             //check next index
hashValue %=array.length;
probeCount++;
} //end while
System.out.printf("%-40s %-7s %-8s %-12s %-12d %n", key, "", "yes", "", probeCount);
lpFailedDeletes++;         //if the key is null, 
lpFailedDeletesPL +=probeCount;

}// end findLP

/////////////////////////////////////////////////////////////////////////////////////////////////////Delete QP

public static void deleteQP(String key, stringInt[] array){
int probeCount = 0; 

int hashValue = hashFunc3(key, array.length, key.length());
int temp = hashValue;
probeCount++;//initial prob++

while(array[temp].getKey() != null){
if(array[temp].getKey().equals(key)){
qpSuccessfulDeletes++;
qpSuccessfulDeletesPL+= probeCount;
System.out.printf("%-40s %-7s %-8s %-12d %-12s %n", array[temp].getKey(), "yes", "", probeCount, "");
array[temp].setKey(" ");
return;
}// end if

temp = hashValue + (int)Math.pow(probeCount,2);  //check next index
temp %= array.length;
probeCount++;
} //end while
System.out.printf("%-40s %-7s %-8s %-12s %-12d %n", key, "", "yes", "", probeCount);
qpFailedDeletes++;         //if the key is null, 
qpFailedDeletesPL +=probeCount;

}// end deleteQP


/////////////////////////////////////////////////////////////////////////////////////////////////
 public static void main(String[] args)throws FileNotFoundException{
 
 File f1 = new File(args[0]);
 File f2 = new File(args[1]);
 File f3 = new File(args[2]);
 ///////////////////////////////////////////////////////////////////////////////////////////////Read in array from first file
 long fileCount = 0;

 try{
 
 Scanner scan = new Scanner(f1);
 while(scan.hasNext()){
 scan.next();
 fileCount++;
 }//end while
 scan.close();
 }//end try

 catch(IOException e){
 e.printStackTrace();
 }//end catch
 
  ////////////////////////////////////////////////////////////////////////////////////////////////Determine array size
 BigInteger nextPrimeOfTwoN = BigInteger.valueOf(fileCount * 2);
 int arraySize = nextPrimeOfTwoN.nextProbablePrime().intValue();
 ////////////////////////////////////////////////////////////////////////////////////////////////Create Arrays
 stringInt[] a = new stringInt[arraySize];
 for(int i = 0; i< a.length; i++){
 a[i] = new stringInt(null, 0);
 }
 stringInt[] b = new stringInt[arraySize];
 for(int i = 0; i< a.length; i++){
 b[i] = new stringInt(null, 0);
 }
 //System.out.print(hashFunc3("luck", arraySize, 4));

//////////////////////////////////////////////////////////////////////////////////////////////// LP Insert + Display insert stats
//Display header info
System.out.println("           A(insert)");
System.out.println("index   string                                   probe length for insertion");
Scanner scan1 = new Scanner(f1);
/////////////////////// Build the Insert Array and list of probe lengths
while(scan1.hasNext()){
 String str = scan1.next();
 insertLP(str, a);
 }//end while
 scan1.close();
/////////////////////// Display
for (int i = 0; i< a.length; i++){
if(a[i].getKey()!= null && !a[i].getKey().equals(" ")){
System.out.printf("%-7d %-40s %-7d %n", i, a[i].getKey(), a[i].getProbeLength());
}
}

 ///////////////////////Average probe length
lpInsertAvgPl = (double) lptotalInsertPL/lpTotalInsertions;
System.out.println("ave probe length:      " + Math.round(lpInsertAvgPl*100.0)/100.0);
System.out.println();//extra space
    
////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////// QP Insert + Display insert stats
//while the file still has elements, read them in and insert into array A.Then display
System.out.println("           B(insert)");
System.out.println("index   string                                   probe length for insertion");
Scanner scan2 = new Scanner(f1);
while(scan2.hasNext()){
 String str = scan2.next();
 insertQP(str, b);
 }//end while
 scan2.close();

for (int i = 0; i< b.length; i++){
if(b[i].getKey()!= null && !b[i].getKey().equals(" ")){
System.out.printf("%-7d %-40s %-7d %n", i, b[i].getKey(), b[i].getProbeLength());
}
} 
 
qpInsertAvgPl = (double) qptotalInsertPL/qpTotalInsertions;
System.out.println("ave                     " + Math.round(qpInsertAvgPl*100.0)/100.0);
System.out.println();//extra space
///////////////////////////////////////////////////////////////////////////////////////////////LP Find
System.out.println("           A(find)");
System.out.println("String                                   Success Failure  PL Success   PL Failure");
Scanner scan3 = new Scanner(f2);
while(scan3.hasNext()){
 String str = scan3.next();
 findLP(str, a);
 }//end while
 scan3.close();


lpFindSuccessAvgPl = (double) lpSuccessfulFindsPL/lpSuccessfulFinds;
lpFindFailAvgPl = (double)lpFailedFindsPL/lpFailedFinds;
System.out.println("average probe length:                                     " + Math.round(lpFindSuccessAvgPl*100.0)/100.0 + "         " + Math.round(lpFindFailAvgPl*100.0)/100.0);
System.out.println();//extra space    
//////////////////////////////////////////////////////////////////////////////////////////////// QP Find

System.out.println("           B(find)");
System.out.println("String                                   Success Failure  PL Success   PL Failure");
Scanner scan4 = new Scanner(f2);
while(scan4.hasNext()){
 String str = scan4.next();
 findQP(str, b);
 }//end while
 scan4.close();


qpFindSuccessAvgPl = (double) qpSuccessfulFindsPL/qpSuccessfulFinds;
qpFindFailAvgPl = (double)qpFailedFindsPL/qpFailedFinds;
System.out.println("average probe length:                                     "+ Math.round(qpFindSuccessAvgPl*100.0)/100.0 + "         " + Math.round(qpFindFailAvgPl*100.0)/100.0);
System.out.println();//extra space
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////LP Delete
System.out.println("           A(delete)");
System.out.println("String                                   Success Failure  PL Success   PL Failure");
Scanner scan5 = new Scanner(f3);
while(scan5.hasNext()){
 String str = scan5.next();
 deleteLP(str, a);
 }//end while
 scan5.close();


lpDeleteSuccessAvgPl = (double)lpSuccessfulDeletesPL/lpSuccessfulDeletes;
lpDeleteFailAvgPl = (double)lpFailedDeletesPL/lpFailedDeletes;
System.out.println("average probe length:                                     " + Math.round(lpDeleteSuccessAvgPl*100.0)/100.0 + "         " + Math.round(lpDeleteFailAvgPl*100.0)/100.0);
System.out.println();//extra space
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////QP Delete
System.out.println("           B(delete)");
System.out.println("String                                   Success Failure  PL Success   PL Failure");
Scanner scan6 = new Scanner(f3);
while(scan6.hasNext()){
 String str = scan6.next();
 deleteQP(str, b);
 }//end while
 scan6.close();


qpDeleteSuccessAvgPl = (double)qpSuccessfulDeletesPL/qpSuccessfulDeletes;
qpDeleteFailAvgPl = (double)qpFailedDeletesPL/qpFailedDeletes;
System.out.println("average probe length:                                     " + Math.round(qpDeleteSuccessAvgPl*100.0)/100.0 + "         " + Math.round(qpDeleteFailAvgPl*100.0)/100.0);
System.out.println();//extra space
}//end main
}//end class
mkdir constant dao exception model service
run="run.sh"
echo 'find . -type f -name "*.class" -delete' > $run
echo "javac Driver.java" >> $run
echo "java Driver" >> $run
clear="clear.sh"
echo 'find . -type f -name "*.class" -delete' > $clear
chmod +x $run
chmod +x $clear
driverFile="Driver.java"
echo 'public class Driver {
    public static void main (String args[]){
    }
    }' > $driverFile
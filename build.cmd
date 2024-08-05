javac -d classes -classpath "lib/*" src/com/seafooddelakec/client/*.java src/com/seafooddelakec/app/*.java src/com/seafooddelakec/menu/*.java src/com/seafooddelakec/*.java

jar --create --file seafooddelakec-1.0.jar -C classes .

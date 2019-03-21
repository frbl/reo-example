# Reo example
The following repo contains a simple example to build a coordinated workflow.

## Getting started 

Download the compiler and run `installer.sh`

```
  wget https://github.com/ReoLanguage/Reo/raw/master/archive/reo-linux_mac.zip
  unzip reo-linux_mac.zip
  sh ./reo-linux_mac/installer.sh
```

Run and compile the reo program to Java

```
  # Set the correct library paths
  source ./reo-linux_mac/initreo.sh
  java -jar reo-linux_mac/bin/reo-1.0.jar Main.treo
```

Compile the produced Java file using the java compiler
```
  javac Main.java
```

Run the application
```
  java Main
```


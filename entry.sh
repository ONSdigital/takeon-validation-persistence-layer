#!/bin/ash
	packages=(dpkg-query -Wf '${Package;-40}${Essential}\n' | grep no | awk '!/java*|jre*/ {print$1}')
	for package in $packages
	do
	echo $package
	apt-get remove $package -y
	done
	java -jar /validation_pl/validation-persistence-layer-1.0-SNAPSHOT.jar


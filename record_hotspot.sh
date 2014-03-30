#!/bin/bash

VERT="\\033[1;32m"
NORMAL="\\033[0;39m"
ROUGE="\\033[1;31m"
ROSE="\\033[1;35m"
BLEU="\\033[1;34m"
BLANC="\\033[0;02m"
BLANCLAIR="\\033[1;08m"
JAUNE="\\033[1;33m"
CYAN="\\033[1;36m"

can_continue=1

# welcome message

echo "
  _    _  ____ _______ _____ _____   ____ _______       
 | |  | |/ __ \__   __/ ____|  __ \ / __ \__   __|      
 | |__| | |  | | | | | (___ | |__) | |  | | | |         
 |  __  | |  | | | |  \___ \|  ___/| |  | | | |         
 | |  | | |__| | | |  ____) | |    | |__| | | |         
 |_|__|_|\____/ _|_|_|_____/|_|__  _\____/__|_|_ _____  
 |  __ \|  ____/ ____/ __ \|  __ \|  __ \|  ____|  __ \ 
 | |__) | |__ | |   | |  | | |__) | |  | | |__  | |__) |
 |  _  /|  __|| |   | |  | |  _  /| |  | |  __| |  _  / 
 | | \ \| |___| |___| |__| | | \ \| |__| | |____| | \ \ 
 |_|  \_\______\_____\____/|_|  \_\_____/|______|_|  \_\\
 "

echo "Checking if all packages are installed"


# checking packages 
if [ -f /usr/sbin/apache2 ]; then
	echo -e $VERT"\t* Apache is installed"$NORMAL
else
	echo -e $ROUGE"\t* Apache is missing"$NORMAL
	can_continue=0
fi

if [ -f /usr/bin/python ]; then
	echo -e $VERT"\t* Python is installed"$NORMAL
else
	echo -e $ROUGE"\t* Python is missing"$NORMAL
	can_continue=0
fi

if [ -f /usr/bin/mysql ]; then
	echo -e $VERT"\t* MySQL is installed"$NORMAL
else
	echo -e $ROUGE"\t* MySQL is missing"$NORMAL
	can_continue=0
fi


if [ $can_continue -eq 1 ]; then

	echo "Now it will run a python script to record all hotspot wifi next to you"

	var=`iwlist wlan0 scan | ./test.py`

	# we are going to change variable separator to carriage return
	oldIFS="$IFS"
	IFS='
	'
	IFS=${IFS:0:1} # this is useful to format your code with tabs
	lines=($var)

	iterator=0
	# now to space
    IFS=' '
	for line in "${lines[@]}"
	    do
	    	if [ $iterator -ne 0 ]; then
		        hotspot=($line)
		        # at this line, we have in hotspot each informations
		        echo "${hotspot[@]}"
	    	fi
	        iterator=$(($iterator + 1))
	done
	IFS="$oldIFS"

else

	echo "Please install all required packages"
	echo "You can run this command (will need root access)"
fi
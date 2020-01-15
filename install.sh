#!/usr/bin/bash
# Author: Jeffry Johar
# Date: 1/15/2020
# About: bb installer

OS=$(uname -o)

case $OS in
	"GNU/Linux")
	  echo "Linux rocks"
	  echo "cp bb /usr/local/bin"
	  cp bb /usr/local/bin
	  ;;

	 "Android")
           echo "Android rocks"
           if [[ -e "/data/data/com.termux/files/usr/bin/" ]]
	   then
	     echo "cp bb /data/data/com.termux/files/usr/bin/"
	     cp bb /data/data/com.termux/files/usr/bin/
           else
             echo "termux dir not found."
             echo "copy bb to $HOME and use it as \"./bb\" "
           fi	  
	 ;;
esac	 

#!/usr/bin/bash
# Author: Jeffry Johar
# Date: 1/14/2020
# About: A ssh session manager for Linux

#### global variable ####
#get server list from home directory
if [[ -f "$HOME/.servers.lst" ]];
then
  server="$HOME/.servers.lst"
else
  echo "$HOME/.servers.lst does not exist. Please create one"
  exit
fi

#check if user grep any variable from the servers.lst
#clear nuserver
> $HOME/.nuserver.lst

if [[ -n "$1" ]];
then 
  grep $1 $server > $HOME/.nuserver.lst 
else
  cp -f $HOME/.servers.lst $HOME/.nuserver.lst 	
fi
nuserver="$HOME/.nuserver.lst"

#if file is empty the grep is finding something that doesn't exist
if [[ ! -s "$nuserver" ]];
then
   echo "$1 does not exists"
   exit
fi

srvArray+=(x)
num=1

divider=--------------------------------------------

header="%2s %-10s %-15s %-15s\n"
format="%2s %-10s %-15s %-15s\n"

#### main function ####
printf "$header" "##" "User" "Server I.P" "Description"
echo $divider
while IFS=" " read -r user server_ip desc
do
	printf "$format" $num $user $server_ip $desc
	srvArray+=($user@$server_ip)
	((num++))
done < "$nuserver"

echo
read -p "Enter the number or q to quit  : " nsNum

#check if selected number valid
if (( $nsNum > 0 && $nsNum < $num ));
then
  echo "ssh ${srvArray[$nsNum]}"
  ssh ${srvArray[$nsNum]}
elif [[ $nsNum ==  'q' ]]
then
  echo "Bye Bye"
else
  echo "$nsNum is an invalid selection"
fi

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

srvArray+=(x)
num=1

divider=--------------------------------------------

header="%2s %-8s %-15s %-20s\n"
format="%2s %-8s %-15s %-20s\n"

#### main function ####
printf "$header" "##" "User" "Server I.P" "Description"
echo $divider
while IFS=" " read -r user server_ip desc
do
	#echo "$num $count user = $user server = $server_ip desc = $desc"
	printf "$format" $num $user $server_ip $desc
	srvArray+=($user@$server_ip)
	((num++))
done <"$server"

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

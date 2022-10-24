As needed, implementation changes should happen in Main as setter injection
	
Counter file rules: 
	when deleting gmail backup folders, counter value should be most recent folder number +1
	when new folder is created in program run, counter will be updated after new backup is created

SMTP:
	The smtp server name is hard-coded into python, specifically app.py. I tried the same approach that worked with the other str values in the SMTPInfo file, but for some reason the 
	server name just refused to work. 
	
	The final HTML, which is already concatonated with the header, is stored in the same file. If you end up sending multiple
	emails, the program is designed to use the same physical text file and run the Python script N number of times. 
	The whole point of HTMLFinal.txt is to bridge the gap between Java and Python, it's not meant to store anything
	other than the code Python will send out with one mailing session. 

	Port number, sender address, username, and password for the smtp server request are determined in Java. These values are hard-coded. They are read from smtpinfo.txt by app.py.

	The "From: xyz" field is determined in Java, it's hardcoded in CreativeHandler.concatHTMLHeader().
	This is not the same as the "send_adr" used in the SMTP request in python, this is the string literal used in the HTML header.


The SMTPInfo and HTMLFull file paths are both hardcoded into Python and Java, it ended up being superflous to re-read the filepath for HTMLInfo every time when the filename 
	should never change. 

	
The new Office 365 email is SCRUBBED.  The password is SCRUBBED.
smtp name: smtp.office365.com
port: 587 (like normal)
Encryption: STARTTLS


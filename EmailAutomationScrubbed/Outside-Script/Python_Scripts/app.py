import smtplib
from itertools import islice
import report
import datetime
import io

smtp_info_path = "c:/Users/SCRUBBED/Desktop/Tools Proper Temp/Official Projects/EmailAutomation/Outside-Script/Python_Scripts/SMTPInfo.txt"
html_path = "c:/Users/SCRUBBED/Desktop/Tools Proper Temp/Official Projects/EmailAutomation/Outside-Script/Python_Scripts/HTMLFinal.txt"

litht = ["obvious.burner333@gmail.com", "nyx@vulcantech.solutions"]
#smtp_name = ""
smtp_port = ""
send_adr = ""
username = ""
password = ""
adr_count = ""
adr_list = ""
#html_full = ""

with open(smtp_info_path) as f:
    #smtp_name = f.readline()
    smtp_port = f.readline()
    send_adr = f.readline()
    username = f.readline()
    password = f.readline()
    #html_path = f.readline()
    adr_count = f.readline()
    adr_count = int(adr_count)

    lines_gen = islice(f, adr_count)
    adr_list = list(lines_gen)

smtp_port = int(smtp_port)

# reads raw html
with open(html_path, "r", io.DEFAULT_BUFFER_SIZE, "utf-8") as f:
    html_as_list = f.readlines()
formatter = "".join(html_as_list)
mess = formatter.encode('utf-8')


server = smtplib.SMTP("smtp.gmail.com", smtp_port)
server.ehlo()
server.starttls()
server.ehlo()
server.login("vulcantechimp@gmail.com", "SCRUBBED")


print("Mail sent probably successfully")
# server.quit()


clock = datetime.datetime.now()
clock = clock.strftime('%c')


reporter = report.Report(adr_list, "email name", clock)
reporter.create_report()
reporter.send_report()

import smtplib


class Report:
    def __init__(self, adr_list, email_name, timex):
        self.adr_list = adr_list
        self.email_name = email_name
        self.timex = timex
        self.body = ""

    def create_report(self):
        header = "From: vulcantechimp@gmail.com\nTo: vulcantechnologysolutions@gmail.com\nSubject:Ram Mailing Report\n"
        line2 = "Mailing was sent at : " + self.timex + "\n"
        line3 = "These addresses recieved emails: \n"
        lines = []
        # for adr in self.adr_list:
        #   lines.append(adr)
        #lines = "".join(lines)
        self.body = header + line2 + line3
        #self.body = self.body.encode('utf-8')
        print(self.body)

    def send_report(self):
        server = smtplib.SMTP("smtp.gmail.com", 587)
        server.ehlo()
        server.starttls()
        server.ehlo()
        server.login("vulcantechimp@gmail.com", "SCRUBBED")
        server.sendmail("vulcantechimp@gmail.com",
                        "vulcantechnologysolutions@gmail.com", self.body)
        server.quit()

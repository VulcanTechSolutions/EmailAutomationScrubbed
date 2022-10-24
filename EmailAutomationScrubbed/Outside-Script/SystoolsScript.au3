WinActivate("SysTools Gmail Backup 9.2", "")
WinSetState("SysTools Gmail Backup 9.2", "", @SW_MAXIMIZE)



;LOGIN
Sleep(2000)
ControlClick("SysTools Gmail Backup 9.2", "", "[CLASS:WindowsForms10.BUTTON.app.0.3917f2_r6_ad1; TEXT:Login; INSTANCE:1]")

;select backup tab
Sleep(3000)
MouseClick("", 312, 200, 5)

;documents
Sleep(200)
MouseClick ( "", 858, 612, 5)

;contacts
Sleep(200)
MouseClick("", 645, 598, 5)

;calendars
Sleep(200)
MouseClick("", 442, 598, 5)

;format drop-down initial
ControlClick("SysTools Gmail Backup 9.2", "", "[CLASS:WindowsForms10.COMBOBOX.app.0.3917f2_r6_ad1; INSTANCE:2]")

;format drop-down select eml
Sleep(700)
MouseClick("", 144, 480, 5)
;format-label initial
Sleep(200)
MouseClick("", 349, 405, 5)

;format-label select autoincriment
Sleep(200)
MouseClick("", 299, 682, 5)

;backup-to location bar.
ControlClick("SysTools Gmail Backup 9.2", "", "[CLASS:WindowsForms10.EDIT.app.0.3917f2_r6_ad1; TEXT:; INSTANCE:1]")
Sleep(1000)
ControlSend("SysTools Gmail Backup 9.2", "", "[CLASS:WindowsForms10.EDIT.app.0.3917f2_r6_ad1; TEXT:; INSTANCE: 1]", "\Tools Proper Temp\Official Projects\EmailAutomation\CreativeMaterialContainer\Optout")



;apply filter button
Sleep(1000)
MouseClick("", 896, 910, 5)

;vulcantechimp checkbox
MouseClick("", 140, 358, 5)

;submissions-optout checkbox
MouseClick("", 172, 648, 5)



;start
MouseClick("", 1554, 984, 2)

;demo agree
MouseClick("", 870, 608, 2)

;download complete
Sleep(7000)
MouseClick("", 870, 608, 2)


;exit
MouseClick("", 1693, 4, 2)

;confirm exit
MouseClick("", 772, 603, 1)
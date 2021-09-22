#/* *** ODSATag: Payroll *** */
# A simple payroll entry with ID, name, address fields 
class Payroll:
    def __init__(self, inID, inname, inaddr):
        self.ID = inID
        self.name = inname
        self.address = inaddr
    def getID(self): return self.ID
    def getname(self): return self.name
    def getaddr(self): return self.address
#/* *** ODSAendTag: Payroll *** */

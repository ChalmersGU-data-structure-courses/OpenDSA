
#/* *** ODSATag: PayrollTest *** */
# IDdict organizes Payroll records by ID
IDdict = UALDictionary()

# namedict organizes Payroll records by name
namedict = UALDictionary()

foo1 = Payroll(5, "Joe", "Anytown");
foo2 = Payroll(10, "John", "Mytown");

IDdict.insert(foo1.getID(), foo1)
IDdict.insert(foo2.getID(), foo2)
namedict.insert(foo1.getname(), foo1)
namedict.insert(foo2.getname(), foo2)

findfoo1 = IDdict.find(5);
findfoo2 = namedict.find("John");
#/* *** ODSAendTag: PayrollTest *** */


#/* *** ODSATag: Dictp4 *** */
while dict.size() > 0:
    it = dict.removeAny()
    doSomething(it)
#/* *** ODSAendTag: Dictp4 *** */

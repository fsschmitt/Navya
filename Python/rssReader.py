import urllib2
from bs4 import BeautifulSoup

f = open('out.txt','w')

#Publico

#print >>f, '::RSS PUBLICO::\n\n'
f.write('::RSS PUBLICO::\n\n')

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoRSS?format=xml').read())

for item in soup.findAll('item'):
	f.write('Title:  ')
	f.write(item.title.string.encode("utf-8"))
	f.write('\n\n')
	desc = item.description.string
	num = desc.find("<")
	res = desc[0:num]
	f.write('Description:  ')
	f.write(res.encode("utf-8"))
	f.write('\n--------------------------------------\n')

#JN

#print >>f, '\n\n::RSS JN::\n\n'
f.write('\n\n::RSS JN::\n\n')

soup2 = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-ULTIMAS').read())

for item in soup2.findAll('item'):
	f.write('Title:  ')
	f.write(item.title.string.encode("utf-8"))
	f.write('\n\n')
	desc = item.description.string
	num = desc.find("<")
	res = desc[0:num]
	f.write('Description:  ')
	f.write(res.encode("utf-8"))
	f.write('\n--------------------------------------\n')


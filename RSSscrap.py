import urllib2
from bs4 import BeautifulSoup

#f = open('out.txt','w')

#Publico

#print >>f, '::RSS PUBLICO DESPORTO::\n\n'

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoDesporto?format=xml').read())

for item in soup.findAll('item'):
	f = open('Noticias/Desporto/'+item.title.string.encode('UTF-8')+'.txt','w')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Desporto').read())

for item in soup.findAll('item'):
	f = open('Noticias/Desporto/'+item.title.string.encode('UTF-8')+'.txt','w')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))

#print >>f, '::RSS PUBLICO ECONOMIA::\n\n'

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoCultura?format=xml').read())

fileName = 0;
for item in soup.findAll('item'):
	f = open('Noticias/Cultura/'+str(fileName)+'.txt','w')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))
	fileName=fileName+1	

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Cultura').read())

fileName = 0;
for item in soup.findAll('item'):
	f = open('Noticias/Cultura/'+str(fileName)+'.txt','w')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))
	fileName=fileName+1	


soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoPolitica?format=xml').read())

for item in soup.findAll('item'):
	f = open('Noticias/Politica/'+item.title.string.encode('UTF-8')+'.txt','w')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Politica').read())

for item in soup.findAll('item'):
	f = open('Noticias/Politica/'+item.title.string.encode('UTF-8')+'.txt','w')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))	


	

#JN

#print >>f, '\n\n::RSS JN::\n\n'

#soup2 = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-ULTIMAS').read())

#for item in soup2.findAll('item'):
#	desc = item.description.string
#	num = desc.find("<")
#	print desc[0:num]

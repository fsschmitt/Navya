import urllib2
import os
import time
from bs4 import BeautifulSoup

#f = open('out.txt','w+')

#Publico

#print >>f, '::RSS PUBLICO DESPORTO::\n\n'

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoDesporto?format=xml').read())

val = 1;
newpath = r'Noticias/Desporto/'; 
if not os.path.exists(newpath): os.makedirs(newpath)
for item in soup.findAll('item'):
	f = open('Noticias/Desporto/ %i.txt' % (val+time.time()),'w+')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))
	val = val + 1;

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Desporto').read())
for item in soup.findAll('item'):
	f = open('Noticias/Desporto/ %i.txt' % (val+time.time()),'w+')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))
	val = val + 1;

#print >>f, '::RSS PUBLICO ECONOMIA::\n\n'

soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoCultura?format=xml').read())

val = 1;
newpath = r'Noticias/Cultura/'; 
if not os.path.exists(newpath): os.makedirs(newpath)
for item in soup.findAll('item'):
	f = open('Noticias/Cultura/%i.txt' % (val+time.time()),'w+')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))
	val = val + 1;

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Cultura').read())


for item in soup.findAll('item'):
	f = open('Noticias/Cultura/%i.txt' % (val+time.time()),'w+')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))
	val = val + 1;


soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoPolitica?format=xml').read())

val = 1;
newpath = r'Noticias/Politica/'; 
if not os.path.exists(newpath): os.makedirs(newpath)
for item in soup.findAll('item'):
	f = open('Noticias/Politica/%i.txt' % (val+time.time()),'w+')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))
	val = val + 1;


soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Politica').read())

for item in soup.findAll('item'):
	f = open('Noticias/Politica/%i.txt' % (val+time.time()),'w+')
	desc = item.description.string
	num = desc.find("<")
	f.write(item.title.string.encode('UTF-8')+'\n')
	f.write(desc[0:num].encode('UTF-8'))	
	val = val + 1;


	

#JN

#print >>f, '\n\n::RSS JN::\n\n'

#soup2 = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-ULTIMAS').read())

#for item in soup2.findAll('item'):
#	desc = item.description.string
#	num = desc.find("<")
#	print desc[0:num]

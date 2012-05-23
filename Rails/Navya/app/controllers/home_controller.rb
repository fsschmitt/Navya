class HomeController < ApplicationController
  def index
  	@ls = `ls`
  	@economy_politics= ["news1","news2","news3","news4","news5","news 6"]

  	@economia = News.where("category = ?", 1).limit(5)
  	@desporto = News.where("category = ?", 2).limit(5)
  	@cultura_lazer = News.where("category = ?", 3).limit(5)
  	@ciencias_tecnologia = News.where("category = ?", 4).limit(5)
  	@sociedade = News.where("category = ?", 5).limit(5)

  end

  def populate
    
  	`cd db/fixtures; python RSSReader.py;java -jar Classifier.jar;rake db:seed_fu`

  	redirect_to "#"
  end

  
  def addToTrainingSet
    @id = params[:id]
    @categoria = params[:cat]

    @new = News.find(@id)

    @new.update_attribute("category", @categoria)

    @direct = "";
    if @categoria == "1"
      @direct = "EconomiaePolitica/"
    elsif @categoria == "2"
      @direct = "Desporto/"
    elsif @categoria == "3"
      @direct = "CulturaeLazer/"
    elsif @categoria == "4"
      @direct = "CienciaeTecnologia/"
    elsif @categoria == "5"
      @direct = "Sociedade/"
    end


    File.atomic_write("db/fixtures/Noticias/" + @direct + @new.title + ".txt") do |file|
      file.write(@new.title+"\n")
      file.write(@new.url+"\n")
      file.write(@new.text+"\n")
    end

    redirect_to "#"
  end


  def retrainmodel

    `cd db/fixtures; java -jar BuildModel.jar Noticias out.arff 0.01 .80;`

    redirect_to "#"
  end

  def modelstatus

    @stats = `cd db/fixtures; python RSSscrap.py; java -jar StatusModel.jar; cd Noticias2; rm -rf *`
    #@stats = `cd db/fixtures; python RSSscrap.py; java -jar StatusModel.jar;`
    #@stats = `cd db/fixtures; java -jar StatusModel.jar;`
    logger.info(@stats)
    render "/more/status.html"
  end

end

class MoreController < ApplicationController
  def index

	@category = "s"
  	case params[:id]
  	when "1"
  		@category = "Economia e Politica"
  	when "2"
  		@category = "Desporto"
  	when "3"
  		@category = "Cultura e Lazer"
  	when "4"
  		@category = "Ciências e Tecnologia"
  	when "5"
  		@category = "Sociedade"
  	end

	@news = News.where("category = ?", params[:id])
  end
end

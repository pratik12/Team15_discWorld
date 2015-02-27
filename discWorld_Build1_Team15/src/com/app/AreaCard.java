package com.app;

/**
 * 
 * @author Sanchit
 * 		enum  AreaCard
 *		representing the 12 city area cards
 */
	public enum AreaCard {
		
		the_Hippo("The Hippo"), 
		the_Scours("The Scours"),  									
		small_Gods("Small Gods"), 
		dragons_Landing("Dragons Landing"), 
		unreal_Estate("Unreal Estate"), 
		dolly_Sisters("Dolly Sisters"), 
		nap_Hill("Nap Hill"), 
		seven_Sleepers("Seven Sleepers"), 
		isle_of_Gods("Isle of Gods"), 
		longwall("Longwell"), 
		dimwell("Dimwell"), 
		the_Shades("The Shades"); 
		
	
		
	private String area;
	private AreaCard(String area)
	{
			this.area=area;
			
	}

	public String getarea()
	{
		return area;
		
	}
	
	
	
	private AreaCard aCard;
	
	
	public AreaCard addCityAreaCard(String areaname){
		for(AreaCard temp : AreaCard.values() )
		{
			if(temp.getarea().equals(areaname))
					{
						return temp;
					}
		}
		
		
		return null;
	}
	
	public void executeCityAreaAction(AreaCard aCard){
		
		switch (aCard) {
		
		case the_Hippo:
			theHippo();
			break;
			
		case the_Scours:
			theScours();
			break;
		
		case small_Gods:
			smallGods();
			break;
		
		case dragons_Landing:
			dragonsLanding();
			break;

		case unreal_Estate:
			unrealEstate();
			break;
		
		case isle_of_Gods:
			isle_of_Gods();
			break;
		case dolly_Sisters:
			dollySisters();
			break;
		
		case nap_Hill:
			napHill();
			break;
			
		case seven_Sleepers:
			sevenSleepers();
			break;
		
		case longwall:
			longwall();
			break;
		
		case dimwell:
			dimwell();	
			break;
		
		case the_Shades:
			theshades();		
			break;
			
		default:
			break;
		}
	}

	private void theshades() {
		// TODO Auto-generated method stub
		
	}

	private void dimwell() {
		// TODO Auto-generated method stub
		
	}

	private void longwall() {
		// TODO Auto-generated method stub
		
	}

	private void sevenSleepers() {
		// TODO Auto-generated method stub
		
	}

	private void napHill() {
		// TODO Auto-generated method stub
		
	}

	private void dollySisters() {
		// TODO Auto-generated method stub
		
	}

	private void isle_of_Gods() {
		// TODO Auto-generated method stub
		
	}

	private void unrealEstate() {
		// TODO Auto-generated method stub
		
	}

	private void dragonsLanding() {
		// TODO Auto-generated method stub
		
	}

	private void smallGods() {
		// TODO Auto-generated method stub
		
	}

	private void theScours() {
		// TODO Auto-generated method stub
		
	}

	private void theHippo() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	/**
	private void isle_of_Gods() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
			 bank=bank+12;
			 amount=amount-12;
		}else{
			bank = bank-2;
			amount = amount +2;
			BoardGame b1 = null;
			ArrayList<Area> a1 = b1.board_areas;
			Area a2 = null;
			Iterator iterator = ((List<Area>) a2).iterator();
			int i=0; 
			while (iterator.hasNext()) {
		           Area a3 = (Area) iterator.next();
		           if(a3.isTroubleMarkers()==true && i==1){
		        	   a3.setTroubleMarkers(false);
		        	   break;
		           }
		           i++;
		        }
			}
		
	}
	private void theshades() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
		}
		
		
	}

	private void dimwell() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
		}
		
	}

	private void longwall() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
			 bank=bank+12;
			 amount=amount-12;
		}else{
			bank = bank-1;
			amount = amount +1;
			}
		
	}

	private void sevenSleepers() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
			 bank=bank+18;
			 amount=amount-18;
		}else{
			bank = bank-3;
			amount = amount +3;
			}
		
	}

	private void napHill() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
			 bank=bank+12;
			 amount=amount-12;
		}else{
			bank = bank-1;
			amount = amount +1;
			}
		
	}

	private void dollySisters() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
		}
		
	}

	private void unrealEstate() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
		}
		
	}

	private void dragonsLanding() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
			 bank=bank+12;
			 amount=amount-12;
		}else{
			bank = bank-2;
			amount = amount +2;
			}
		
	}

	private void smallGods() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
		}
		
	}

	private void theScours() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
			 bank=bank+6;
			 amount=amount-6;
		}else{
			bank = bank-2;
			
			ArrayList<PlayerCard> p2 = pl.getPlayersPlayingCard();
			if(!p2.isEmpty()){
				 p2.remove(0);
				 pl.playersPlayingCard=p2;
				}
			}
		
	}

	private void theHippo() {
		if(map.get(this.number)==null){
			 map.put(this.number,this.user);
			 bank=bank+12;
			 amount=amount-12;
			 pl.setPlayerAmount(amount);
		}else{
		bank = bank-2;
		amount = amount +2;
		pl.setPlayerAmount(amount);
		}
	}
	**/ 
}

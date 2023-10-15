import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import Chart from 'chart.js/auto';
import { Router } from '@angular/router';
import { AuctionsService } from '../services/auctions.service';



@Component({
  selector: 'app-list-auctions',
  templateUrl: './list-auctions.component.html',
  styleUrls: ['./list-auctions.component.css']
})
export class ListAuctionsComponent implements OnInit{
  currentPage = 1;
  itemsPerPage = 4;
  auctions : any;
  isEmptyResult: boolean = false;
  public chart: any;

  constructor(private auctionService: AuctionsService,  private router : Router) { }

getAllAuctions(){
  
  this.auctionService.GetAllAuctions().subscribe(data =>{
this.auctions = data;
this.isEmptyResult = this.auctions.length === 0;
  });
}

getpercentege() {
  this.auctionService.getPercentage().subscribe(data => {
    // Affiche les données dans la console
    console.log(data);

    // Récupérer les clés et les valeurs de l'objet data
    const keys = Object.keys(data);
    const values = Object.values(data);

    // Mettre à jour les données du graphique avec les résultats de getUserPercentage()
    this.chart.data.labels = keys;
    this.chart.data.datasets[0].data = values;

    // Générer un tableau de couleurs en fonction du nombre de données
    const colors = this.generateColors(values.length);
    this.chart.data.datasets[0].backgroundColor = colors;

    this.chart.update();
  });
}

generateColors(numColors : number) {
  // Générer un tableau de couleurs avec une saturation et une luminosité aléatoires
  const colors = [];
  for (let i = 0; i < numColors; i++) {
    const hue = Math.floor(Math.random() * 360);
    const saturation = Math.floor(Math.random() * 50) + 50; // Saturation entre 50% et 100%
    const lightness = Math.floor(Math.random() * 40) + 30; // Luminosité entre 30% et 70%
    colors.push(`hsl(${hue}, ${saturation}%, ${lightness}%)`);
  }
  return colors;
}

createChart() {
  this.chart = new Chart('MyChart', {
    type: 'pie',
    data: {
      labels: [], // Les étiquettes représentent les identifiants numériques
      datasets: [{
        label: 'My First Dataset',
        data: [], // Les données seront mises à jour à partir de la réponse JSON
        backgroundColor: [], // Les couleurs seront générées à partir des données
        hoverOffset: 4
      }]
    },
    options: {
      aspectRatio: 2.5,
    }
  });
}

reloadComponent(): void {
  const currentRoute = this.router.url.split('?')[0];
  this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    this.router.navigate([currentRoute]);
  });
}

  ngOnInit(){
this.getAllAuctions(); 
this.createChart();
this.getpercentege();
  }
  
}

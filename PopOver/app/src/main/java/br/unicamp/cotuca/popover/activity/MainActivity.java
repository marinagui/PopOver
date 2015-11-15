package br.unicamp.cotuca.popover.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import br.unicamp.cotuca.popover.R;
import br.unicamp.cotuca.popover.model.Place;
import br.unicamp.cotuca.popover.presenter.MainPresenter;
import br.unicamp.cotuca.popover.view.MainView;
import br.unicamp.cotuca.popover.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;

/**
 * Created by joao on 31/08/15.
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener, MainView {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_TIPO = "main_extra_tipo";

    private MainPresenter mPresenter;

    private Place[] places;

    private GoogleMap map;

    private Marker lastOpened;

    private String tipo;

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(!marker.equals(lastOpened)){
            if(lastOpened != null)
                lastOpened.hideInfoWindow();
            lastOpened = marker;
            Log.d(TAG, marker.getTitle());
            marker.showInfoWindow();
        }else{
            if(marker.isInfoWindowShown()){
                marker.hideInfoWindow();
            }else{
                marker.showInfoWindow();
            }
        }
        return true;
    }



    @Override
    public void onInfoWindowClick(Marker marker) {
        for(Place p: places){
            if(p.getName().equals(marker.getTitle())){
                Intent mIntent = new Intent(this, PlaceDetailsActivity.class);
                mIntent.putExtra(PlaceDetailsActivity.EXTRA_PLACE_NAME , p.getName());
                mIntent.putExtra(PlaceDetailsActivity.EXTRA_PLACE_ADRESS, p.getAddress());
                mIntent.putExtra(PlaceDetailsActivity.EXTRA_PLACE_DESCRIPTION, p.getDescription());
                mIntent.putExtra(PlaceDetailsActivity.EXTRA_PLACE_IMAGE_URL, p.getImgURL());
                startActivity(mIntent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIntentExtra();

        setContentView(R.layout.main_activity);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        configAddButton();
        mPresenter = new MainPresenter(this,this);
        configPlaces();
        /*
        */

    }

    private void getIntentExtra(){
        tipo = getIntent().getExtras().getString(EXTRA_TIPO);
    }

    private void configPlaces(){
        places = new Place[14];
        places[0] = new Place(-22.874262,-47.05517,"Parque Portugal (Lagoa do Taquaral)" , "Avenida Doutor Heitor Penteado", "Um lugar que possibilita a prática de exercícios físicos, incluindo a academia da Terceira Idade, oferecendo como lazer a Caravela após a restauração,  o chafariz colorido, a Concha Acústica, animais como pavão e outras aves, pedalinho e lanchonetes, barracas com água de coco e pastel, ciclovia, kartódromo e quadras poliesportivas. Além de tudo é um lugar arborizado. Alerta para entrada de animais silvestres, observado recentemente (como gatos e capivaras – transmissão de doenças como Toxoplasmose e Febre Maculosa)."
                , "http://i.imgur.com/6ZGAdkB.jpg");

        places[1] = new Place(-22.909938,-47.062633,"Estação Campinas (Antiga Estação FEPASA ou Estação Cultura)" , "Praça Marechal Floriano Peixoto", "Uma antiga estação de trem que há pouco tempo foi reformada. Abriga diversos eventos, inclusive o Mercado Mundo Mix. Alerta à manutenção dos banheiros e limpeza da área externa (retirada de materiais antigos – enferrujados)."
                , "https://upload.wikimedia.org/wikipedia/commons/1/1a/Esta%C3%A7%C3%A3o_ferrovi%C3%A1ria_-_centro_cultural_de_Campinas_001.jpg");

        places[2] = new Place(-22.906164,-47.060422,"Catedral Metropolitana de Campinas" , "Rodovia Doutor José Bonifácio Coutinho Nogueira", "Uma Igreja de estilo Neoclássico, construída em 1807, localizada no centro de Campinas. Atualmente, a Igreja está sendo restaurada. Foram feitos diversos estudos para que a mesma tenha a aparência de quando foi construída (principalmente a cor amarela). Alerta à moradores de rua ao redor da Catedral. Necessária intervenção da Prefeitura de Campinas."
                , "https://acidadeinvisivel.files.wordpress.com/2013/06/campinas_f_017.jpg");

        places[3] = new Place(-22.903574,-47.063552,"Mercado Municipal" , "Rua Benjamin Constant", "Um mercado que foi inaugurado em 1908 no centro da cidade, próximo da rodoviária de Campinas. Há a venda de peixes frescos, doces, especiarias e até mesmo artigos de jardinagem com um preço mais acessível. Alerta à limpeza do local, principalmente aqueles que realizam o comércio de peixes. "
                , "http://www.campinas.sp.gov.br/uploads/fotos/2af5b1dadf539d1c3c246a506f1dbba3.jpg");

        places[4] = new Place(-22.890109, -47.076940,"Torre do Castelo Campinas" , "Rua Orlando Carpino", "A Torre do Castelo é um reservatório de água suspenso construído no formato de Castelo. Chama a atenção por ser um ponto geodésico (subindo os 89 degraus da construção, é possível visualizar muitos bairros da cidade), além de possuir no seu telhado a Rosa dos Ventos. Sua construção foi em 1936 e sua última reforma ocorreu em 1998."
                , "http://www.mcjeditora.com.br/portal/wp-content/uploads/2013/07/torre_castelo.jpg");

        places[5] = new Place(-22.903408,-47.056553,"Praça Carlos Gomes" , "Rua Irmã Serafina", "Praça localizada no centro de Campinas que possui palmeiras imperiais e um belo jardim. Recebeu o nome do ilustre compositor epônimo em 1880, tendo como outras homenagens dois monumentos: ao Rui Barbosa e outro ao Tomás Alves. Destinado à acomodação pública e lazer em geral.  Abriga alguns eventos como pequenas orquestras. Atualmente, um dos problemas da praça é o grande foco de andarilhos e insegurança das pessoas que por ali transitam. "
                , "http://v.i.uol.com.br/album/guia/campinas_f_011.jpg");

        places[6] = new Place(-22.908699,-47.049148,"Bosque dos Jequitibás" , "Rua Coronel Quirino", "Criado na época de 1880, funciona como um minizoológico localizado na região central de Campinas, sendo uma dar maiores áreas de lazer da cidade. É um local que abriga alguns animais como leões, onças, pantera e arara azul, quiosques, lanchonetes e playground, além de um espaço de 10 hectares. No seu interior, há o Museu de História Natural.  "
                , "http://www.campinas.sp.gov.br/uploads/fotos/77cb193df7bf24b3dca24c4e19cba90c.jpg");

        places[7] = new Place(-22.909545,-47.048729,"Museu de História Natural" , "Rua Coronel Quirino", "Localiza-se no Bosque dos Jequitibás. Foi inaugurado no dia 20 de maio de 1939 e é uma instituição tombada. É possível visualizar animais empalhados e a descrição de cada um, ossadas de animais extintos (fósseis) e animais com anomalias que sobreviveram um certo período (como animal de duas cabeças). São mais de duas mil peças representando os ecossistemas do Brasil, incluindo a visualização da botânica e alguns minerais. Fácil acesso."
                , "http://www.hotelpremiumcampinas.com.br/blog/wp-content/uploads/2014/03/campinas-museu-bosque.jpg");

        places[8] = new Place(-22.913081,-47.050077,"Estádio Moisés Lucarelli" , "Rua Afonso Pena", "É o estádio que pertence à Associação Atlética Ponte Preta, inaugurado no dia 12 de setembro de 1948. Conhecido como “O Majestoso”, onde há diversos jogos de futebol.  Tem capacidade para 35 mil espectadores. É um local limpo, porém é um estádio sem cobertura. Banheiros devem ser inspecionados. "
                , "http://i.imgur.com/1jp5SI7.jpg");

        places[9] = new Place(-22.910926,-47.043425,"Estádio Brinco de Ouro da Princesa" , "Avenida Imperatriz Dona Teresa Cristina", "Estádio pertencente ao Guarani Futebol Clube. Foi inaugurado em 1953. Conhecido apenas como Brinco de Ouro, onde há diversas partidas de futebol. Abriga um clube com piscinas. Tem capacidade para 29.130 espectadores. É um local de fácil acesso, com pouca cobertura oferecida, é necessária manutenção dos banheiros e cuidado com as piscinas que se encontram vazias no clube (risco de acúmulo de água de chuvas que prolifera  a larva do mosquito da Dengue)."
                , "http://i.imgur.com/T9GgKzJ.jpg");

        places[10] = new Place( -22.902736,-47.059385,"Jóquei Clube" , "Rua Doutor Thomaz Alves", "Localizado no centro da cidade, foi construído em 1925 sendo palco de festas, recitais de piano e violino. Oferece um restaurante e uma casa noturna (Club 88). Restaurado em 2008. Necessário o aumento de segurança no local. Ao redor do Jóquei há muitos usuários de drogas e prostituição. "
                , "https://upload.wikimedia.org/wikipedia/commons/e/e7/Jockey_Club_Campineiro.JPG");

        places[11] = new Place(-22.893196,-47.064689,"Jardim Botânico do Instituto Agronômico" , "Avenida Barão de Itapura", "Localizado ao meio de avenidas com trânsito intenso, contrastando com o visual de belas e antigas árvores, com outras diversas vegetações. Foi fundado por Dom Pedro II, funcionando atualmente como Secretaria de Agricultura e Abastecimento do Estado de São Paulo, a fim de gerar a ciência e tecnologia em negócio agrícola, oferecendo alimentos à população e matérias-primas às indústrias. "
                , "http://farm8.staticflickr.com/7109/7532739924_581237f708_b.jpg");

        places[12] = new Place(-22.871707,-47.048124,"Museu do Café" , "Avenida Doutor Heitor Penteado", "Local arborizado, possui um lago e animais soltos (capivaras, patos e galinhas). O museu foi construído na antiga Fazenda Taquaral, em 1972 e reaberto ao público em 2003. É retratada a história do café na cidade de Campinas, incluindo imagens da época, objetos da produção de café e do antigo proprietário Francisco de Paula Bueno. Alerta aos animais soltos (principalmente capivaras – transmissão de doenças). "
                , "http://i.imgur.com/WCfrKPC.jpg");

        places[13] = new Place(-22.901874,-47.053381,"Centro de Convivência Cultural - Teatro Arena" , "Rua General Osório", "Inaugurado em 1976, abriga arquibancadas (Teatro de Arena) e teatro Carlos Gomes. Há espaços para exposições de arte e artesanatos (Feira Hippie) aos sábados e domingos. Alerta à limpeza do local, abriga moradores de rua e usuários de droga."
                , "http://campinas.sp.gov.br/uploads/fotos/a542592ac4b0ac799a354e02d76a104a.jpg");

        mPresenter.writePlaces(places);
    }

    private void configAddButton(){
        final Intent mIntent = new Intent(this, NewPlaceActivity.class);

        FloatingActionButton button = (FloatingActionButton)findViewById(R.id.main_activity_add);
        button.setDrawable(getResources().getDrawable(R.drawable.plus));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mIntent);
            }
        });

        // se for usuário, não pode visualizar esse botão
        if (tipo == "usuario") {
            button.hide(true);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mPresenter.loadPlaces();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng campinas = new LatLng(-22.9099380, -47.0626330);

        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campinas, 13));

        map = googleMap;

        mPresenter.loadPlaces();

    }



    @Override
    public void displayMarkers(Place[] places) {
        this.places = places;
        map.clear();
        if(places != null)
            for(Place p: places){
                LatLng position = new LatLng(p.getLat(), p.getLng());
                map.addMarker(new MarkerOptions()
                                .title(p.getName())
                                .position(position)
                );

            }
    }
}

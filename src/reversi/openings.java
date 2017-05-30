/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reversi;

import java.util.ArrayList;

/**
 *
 * @author geral
 */


public class openings {
    
    public String rowLabel = "ABCDEFGH";

    public openingClass[] openingMovesArr = new openingClass[196];  
    public openingClass temp_openingClass;
    
    
    
    public void defineOpenings()
    { 
        for (int i=0; i<196; i++)
        {
            openingMovesArr[i]= new openingClass();
        }
        
        this.openingMovesArr[0].moveString = "C4C3D3C5B2";   // X-square opening
        this.openingMovesArr[1].moveString = "C4C3D3C5B3F3"; // Lysons
        this.openingMovesArr[2].moveString = "C4C3D3C5B3F4B5B4C6D6F5"; // Pyramid/Checkerboarding Peasant
        this.openingMovesArr[3].moveString = "C4C3D3C5B4D2C2F4D6C6F5E6F7"; // Mimura Variation II
        this.openingMovesArr[4].moveString = "C4C3D3C5B4D2D6"; // Heath-Bat
        this.openingMovesArr[5].moveString = "C4C3D3C5B4D2E2"; // Iwasaki Variation
        this.openingMovesArr[6].moveString = "C4C3D3C5B4E3"; // Heath-Chimney
        this.openingMovesArr[7].moveString = "C4C3D3C5B5"; // Raccoon Dog
        this.openingMovesArr[8].moveString = "C4C3D3C5B6C6B5"; // Hamilton
        this.openingMovesArr[9].moveString = "C4C3D3C5B6E3"; // Lollipop
        this.openingMovesArr[10].moveString = "C4C3D3C5D6F4B4B6B5C6B3"; // Bat (Piau Continuation 2)
        this.openingMovesArr[11].moveString = "C4C3D3C5D6F4B4B6B5C6F5"; // Melnikov/Bat (Piau Continuation 1)
        this.openingMovesArr[12].moveString = "C4C3D3C5D6F4B4C6B5B3B6E3C2A4A5A6D2"; // Bat (Kling Continuation)
        this.openingMovesArr[13].moveString = "C4C3D3C5D6F4B4E3B3"; // Bat (Kling Alternative)
        this.openingMovesArr[14].moveString = "C4C3D3C5D6F4F5D2B5"; // Aircraft/Feldborg
        this.openingMovesArr[15].moveString = "C4C3D3C5D6F4F5D2G4D7"; // Sailboat
        this.openingMovesArr[16].moveString = "C4C3D3C5D6F4F5E6C6D7"; // Maruoka
        this.openingMovesArr[17].moveString = "C4C3D3C5D6F4F5E6F6"; // Landau
        this.openingMovesArr[18].moveString = "C4C3D3C5F6E2C6"; // Maruoka Buffalo
        this.openingMovesArr[19].moveString = "C4C3D3C5F6E3C6F5F4G5"; // Tanida Buffalo
        this.openingMovesArr[20].moveString = "C4C3D3C5F6F5"; // Hokuriku Buffalo
        this.openingMovesArr[21].moveString = "C4C3E6C5"; // Wing Variation
        this.openingMovesArr[22].moveString = "C4C3F5C5"; // Semi-Wing Variation
        this.openingMovesArr[23].moveString = "C4E3F4C5D6E6"; // Mimura
        this.openingMovesArr[24].moveString = "C4E3F4C5D6F3C6"; // Shaman/Danish
        this.openingMovesArr[25].moveString = "C4E3F4C5D6F3D3C3"; // Iago
        this.openingMovesArr[26].moveString = "C4E3F4C5D6F3E2"; // Bhagat
        this.openingMovesArr[27].moveString = "C4E3F4C5D6F3E6C3D3E2B5F5B3"; // Murakami Variation
        this.openingMovesArr[28].moveString = "C4E3F4C5D6F3E6C3D3E2B5F5B4F6C2E7D2C7"; // Rotating Flat (Kling Continuation)
        this.openingMovesArr[29].moveString = "C4E3F4C5D6F3E6C3D3E2B6F5B4F6G5D7"; // Brightstein
        this.openingMovesArr[30].moveString = "C4E3F4C5D6F3E6C3D3E2B6F5G5F6"; // Rose-Tamenori-Kling
        this.openingMovesArr[31].moveString = "C4E3F4C5D6F3E6C3D3E2D2"; // Greenberg/Dawg
        this.openingMovesArr[32].moveString = "C4E3F4C5D6F3E6C6"; // Ralle
        this.openingMovesArr[33].moveString = "C4E3F4C5E6"; // Horse
        this.openingMovesArr[34].moveString = "C4E3F5B4F3F4E2E6G5F6D6C6"; // No-Cat (Continuation)
        this.openingMovesArr[35].moveString = "C4E3F5E6D3"; // Italian
        this.openingMovesArr[36].moveString = "C4E3F5E6F4C5D6C6F7G5G6"; // Berner
        this.openingMovesArr[37].moveString = "C4E3F6B4"; // Bent Ganglion
        this.openingMovesArr[38].moveString = "C4E3F6E6F5C5C3B4D6C6B5A6B6C7"; // No-Kung (Continuation)
        this.openingMovesArr[39].moveString = "C4E3F6E6F5C5C3C6D3D2E2B3C1C2B4A3A5B5A6A4A2"; // F.A.T. Draw
        this.openingMovesArr[40].moveString = "C4E3F6E6F5C5C3C6D6"; // Lighning Bolt
        this.openingMovesArr[41].moveString = "C4E3F6E6F5C5C3G5"; // Kung
        this.openingMovesArr[42].moveString = "C4E3F6E6F5C5D3"; // Leader's Tiger
        this.openingMovesArr[43].moveString = "C4E3F6E6F5C5D6"; // Brightwell
        this.openingMovesArr[44].moveString = "C4E3F6E6F5C5F4G5G4F3C6D3D6B3C3B4E2B6"; // Mainline Tiger
        this.openingMovesArr[45].moveString = "C4E3F6E6F5C5F4G6F7D3"; // Tamenori
        this.openingMovesArr[46].moveString = "C4E3F6E6F5C5F4G6F7G5"; // Central Rose-Bill/Dead Draw 
        this.openingMovesArr[47].moveString = "C4E3F6E6F5G6E7C5"; // Brightwell
        this.openingMovesArr[48].moveString = "C4E3F6E6F5C5D6"; // Aubrey (Feldborg Continuation)

        for (int i = 0; i < 49; i++)
        {
            this.openingMovesArr[49 + i].moveString = findSymetric(this.openingMovesArr[i].moveString, 1);
        }
        for (int i = 0; i < 49; i++)
        {
            this.openingMovesArr[98 + i].moveString = findSymetric(this.openingMovesArr[i].moveString, 2);
        }
        for (int i = 0; i < 49; i++)
        {
            this.openingMovesArr[147 + i].moveString = findSymetric(this.openingMovesArr[i].moveString, 3);
        }
    }
    
    public String findSymetric(String p, int type)
    {
        String sMove = "";
        for (int i = 0; i < p.length(); i += 2)
        {
            String oneMove = p.substring(i, i+2);
            sMove += calcSymectic(oneMove, type);
        }
        return sMove;
    }

    public String calcSymectic(String oneMove, int type)
    {
        String sResult = "";
        int nA;
        int nN;
        
        switch (type)
        {
            
            case 1:
                nA = this.rowLabel.indexOf(oneMove.substring(0, 1));
                nN = Integer.parseInt(oneMove.substring(1, 2)) - 1;
                sResult = this.rowLabel.substring(nN, nN+1) + Integer.toString(nA + 1);
                break;
            case 2:
                {
                    int b = this.rowLabel.indexOf(oneMove.substring(0, 1));
                    int c = Integer.parseInt(oneMove.substring(1, 2)) - 1;
                    nA = this.rowLabel.indexOf(oneMove.substring(0, 1));
                    nN = Integer.parseInt(oneMove.substring(1, 2)) - 1;

                    if (nA < 4)
                    {
                        nA = 3 + (4 - nA);
                    }
                    else
                    {
                        nA = 3 - (nA - 4);
                    }
                    if (nN < 4)
                    {
                        nN = 3 + (4 - nN);
                    }
                    else
                    {
                        nN = 3 - (nN - 4);
                    }
                    sResult = this.rowLabel.substring(nA,nA+1) + Integer.toString(nN + 1);

                    break;
                }
            case 3:
                nA = this.rowLabel.indexOf(oneMove.substring(0, 1));
                nN = Integer.parseInt(oneMove.substring(1, 2)) - 1;
                sResult = this.rowLabel.substring(7 - nN,7 - nN+1) + Integer.toString(7 - nA + 1);
                break;

            default:
                break;
        }
        return sResult;
    }
   
}

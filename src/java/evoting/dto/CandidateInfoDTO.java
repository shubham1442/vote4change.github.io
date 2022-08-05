/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;


public class CandidateInfoDTO {
    
     private String candidateId;
     private String party;
      private String symbol;
       private String candidatename;
       
       
    public CandidateInfoDTO()
    {
        
    }

    @Override
    public String toString() {
        return "CandidateInfoDTO{" + "candidateId=" + candidateId + ", party=" + party + ", symbol=" + symbol + ", candidatename=" + candidatename + '}';
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCandidatename() {
        return candidatename;
    }

    public void setCandidatename(String candidatename) {
        this.candidatename = candidatename;
    }

    public CandidateInfoDTO(String candidateId, String party, String symbol, String candidatename) {
        this.candidateId = candidateId;
        this.party = party;
        this.symbol = symbol;
        this.candidatename = candidatename;
    }
    
   
       
}

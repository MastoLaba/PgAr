package it.unibs.ing.ieee.clinicamedica;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Appuntamento implements Comparable<Appuntamento>{
	private int id;
	private Paziente paziente;
	private Medico medico;
	private GregorianCalendar data;
	private int priorita;

	public Appuntamento(Paziente paziente, Medico medico, GregorianCalendar data, int priorita) {
		super();
		this.paziente = paziente;
		this.medico = medico;
		this.data = data;
		this.priorita = priorita;

		id = this.hashCode();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Paziente getPaziente() {
		return paziente;
	}

	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}

	public int getPriorita() {
		return priorita;
	}

	public void setPriorita(int priorita) {
		this.priorita = priorita;
	}

	@Override
	public String toString() {
		return String.format("ID: %d%nMedico: %s %nPaziente: %s%nOrario: %s/%s/%s - %s:%s%nPriorità:%d%n",id, medico.getNome() + " " + medico.getCognome(),
				paziente.getNome() + " " + paziente.getCognome(),
				data.get(Calendar.DAY_OF_MONTH) , data.get(Calendar.MONTH) , data.get(Calendar.YEAR) 
						, data.get(Calendar.HOUR_OF_DAY) , data.get(Calendar.MINUTE),priorita);
	}

	@Override
	public int compareTo(Appuntamento a) {
		return data.compareTo(a.getData());
	}

}

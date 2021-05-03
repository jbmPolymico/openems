export const TRANSLATION = {
    General: {
        active: 'Actief',
        actualPower: 'e-car Laad vermogen',
        autarchy: 'Autarkie',
        automatic: 'Automatisch',
        cancel: 'annuleren',
        capacity: 'Hoedanigheid',
        changeAccepted: 'Wijziging geaccepteerd',
        changeFailed: 'Wijziging mislukt',
        chargeDischarge: 'Debet/ontlaad',
        chargePower: 'Laad vermogen',
        componentCount: 'Aantal componenten',
        componentInactive: 'Component is inactief!',
        connectionLost: 'Verbinding verbroken. Probeer opnieuw verbinding te maken.',
        consumption: 'Verbruik',
        cumulative: 'Cumulatieve Waarden',
        currentValue: 'Huidige waarde',
        dateFormat: 'dd-MM-yyyy', // e.g. German: dd.MM.yyyy, English: yyyy-MM-dd (dd = Day, MM = Month, yyyy = Year)
        digitalInputs: 'Digitale Ingangen',
        directConsumption: 'Directe consumptie',
        dischargePower: 'Ontlaad vermogen',
        fault: 'Fout',
        grid: 'Net',
        gridBuy: 'Netafname',
        gridBuyAdvanced: 'Netafname',
        gridSell: 'Netteruglevering',
        gridSellAdvanced: 'Netteruglevering',
        history: 'Historie',
        inactive: 'Inactief',
        info: 'Info',
        inputNotValid: 'Invoer ongeldig',
        insufficientRights: 'Onvoldoende rechten',
        live: 'Live',
        load: 'Laden',
        manually: 'handmatig',
        measuredValue: 'Gemeten Waarde',
        mode: 'Mode',
        more: 'Meer…',
        noValue: 'Geen waarde',
        off: 'Van',
        offGrid: 'Geen Netaansluiting!',
        ok: 'ok',
        on: 'Naar',
        otherConsumption: 'andere consumptie',
        percentage: 'Procent',
        periodFromTo: 'van {{value1}} tot {{value2}}', // value1 = beginning date, value2 = end date
        phase: 'Fase',
        phases: 'Fases',
        power: 'Vermogen',
        production: 'Opwekking',
        reportValue: 'Rapporteer beschadigde gegevens',
        selfConsumption: 'Eigen consumptie',
        soc: 'Laadstatus',
        state: 'Staat',
        storageSystem: 'Batterij',
        systemState: 'Systeemstatus',
        total: 'totale verbruik',
        totalState: 'Algemene staat',
        warning: 'Waarschuwing',
        Week: {
            monday: 'Maandag',
            tuesday: 'Dinsdag',
            wednesday: 'Woensdag',
            thursday: 'Donderdag',
            friday: 'Vrijdag',
            saturday: 'Zaterdag',
            sunday: 'Zondag',
        },
        Month: {
            january: 'Januari',
            february: 'Februari',
            march: 'Maart',
            april: 'April',
            may: 'Mei',
            june: 'Juni',
            july: 'Juli',
            august: 'Augustus',
            september: 'September',
            october: 'Oktober',
            november: 'November',
            december: 'December',
        },
    },
    Menu: {
        aboutUI: 'Over OpenEMS UI',
        edgeSettings: 'OpenEMS Edge instellingen',
        generalSettings: 'Algemene instellingen',
        index: 'Overzicht',
        logout: 'Uitloggen',
        menu: 'Menu',
        overview: 'OpenEMS Edge overzicht',
        settings: 'Instellingen',
        user: 'Gebruiker',
    },
    Index: {
        allConnected: 'Alle verbindingen gemaakt.',
        connectionFailed: 'Verbinding met {{ value } } mislukt.', // (value = Name vom Websocket)
        connectionSuccessful: 'Succesvol verbonden met {{value }}.', // (value = Name vom Websocket)
        isOffline: 'OpenEMS is offline!',
        toEnergymonitor: 'Naar Energiemonitor...',
    },
    Edge: {
        Index: {
            Energymonitor: {
                activePower: 'Actief vermogen',
                consumptionWarning: 'Verbruik & onbekende producenten',
                gridMeter: 'Energiemeter',
                productionMeter: 'Productiemeter',
                reactivePower: 'Blind vermogen',
                storage: 'Batterij',
                title: 'Energiemonitor',
            },
            Widgets: {
                autarchyInfo: 'Autarky geeft het percentage huidig ​​vermogen aan dat kan worden gedekt door opwekking en ontlading van de opslag.',
                phasesInfo: 'De som van de afzonderlijke fasen kan om technische redenen enigszins afwijken van het totaal.',
                selfconsumptionInfo: 'Eigen verbruik geeft het percentage van de momenteel gegenereerde uitvoer aan dat kan worden gebruikt door direct verbruik en opslagbelasting zelf.',
                twoWayInfoGrid: 'Negative Werte entsprechen Netzeinspeisung, Positive Werte entsprechen Netzbezug',
                twoWayInfoStorage: 'Negative Werte entsprechen Speicher Beladung, Positive Werte entsprechen Speicher Entladung',
                Channeltreshold: {
                    output: 'uitgang'
                },
                Singlethreshold: {
                    above: 'Over',
                    behaviour: 'Gedrag',
                    below: 'Beneden',
                    currentValue: 'Huidige waarde',
                    dependendOn: 'Afhankelijk van',
                    minSwitchingTime: 'Minimum omschakeling',
                    moreThanMaxPower: 'De waarde mag niet lager zijn dan het maximale vermogen van het gecontroleerde apparaat',
                    other: 'Anders',
                    relationError: 'Drempel moet groter zijn dan de geschakelde belasting',
                    switchedLoadPower: 'Geschakelde belasting',
                    switchOffAbove: 'Uitschakelen via',
                    switchOffBelow: 'Schakel uit onder',
                    switchOnAbove: 'Inschakelen via',
                    switchOnBelow: 'Inschakelen onder',
                    threshold: 'Thresholded',
                },
                Peakshaving: {
                    asymmetricInfo: 'De ingevoerde prestatiewaarden verwijzen naar afzonderlijke fasen. Het is aangepast aan de meest gestresste fase.',
                    endDate: 'Einddatum',
                    endTime: 'Eindtijd',
                    mostStressedPhase: 'Meest gestresste fase',
                    peakshaving: 'Piek scheren',
                    peakshavingPower: 'Afvoer voorbij',
                    recharge: 'Laadvermogen',
                    rechargePower: 'Bezig met laden onder',
                    relationError: 'Ontladingslimiet moet groter zijn dan of gelijk zijn aan de belastingslimiet',
                    startDate: 'Startdatum',
                    startTime: 'Starttijd',
                    startTimeCharge: 'Starttijd laden',
                },
                DelayedSellToGrid: {
                    sellToGridPowerLimit: 'Laad hierboven op',
                    continuousSellToGridPower: 'Ontlading hieronder',
                    relationError: 'De kostenlimiet moet groter zijn dan de ontladingslimiet',
                },
                CHP: {
                    highThreshold: 'hoge drempel',
                    lowThreshold: 'Lage drempelwaarde',
                },
                EVCS: {
                    activateCharging: 'Activeer het laadstation',
                    amountOfChargingStations: 'Aantal laadstations',
                    cable: 'Kabel',
                    cableNotConnected: 'Kabel is niet aangesloten',
                    carFull: 'Auto is vol',
                    chargeLimitReached: 'Oplaadlimiet bereikt',
                    chargeMode: 'laadmodus',
                    chargeTarget: 'Lading doel',
                    charging: 'Is aan het laden',
                    chargingLimit: 'Laadlimiet',
                    chargingPower: 'Oplaadvermogen',
                    chargingStation: 'Laadstation',
                    chargingStationCluster: 'Laadstation cluster',
                    chargingStationDeactivated: 'Laadstation gedeactiveerd',
                    chargingStationPluggedInEV: 'Laadstation + E-Auto aangesloten',
                    chargingStationPluggedInEVLocked: 'Laadstation + E-Auto aangesloten + op slot',
                    chargingStationPluggedInLocked: 'Laadstation aangesloten + op slot',
                    charingStationPluggedIn: 'Laadstation aangesloten',
                    clusterConfigError: 'Er is een fout opgetreden in de configuratie van het Evcs-cluster',
                    currentCharge: 'Huidige lading',
                    energieSinceBeginning: 'Energie sinds de laatste lading start',
                    energyLimit: 'Energielimiet',
                    enforceCharging: 'Handhaaf het laden',
                    error: 'Fout',
                    maxEnergyRestriction: 'Beperk de maximale energie per lading',
                    notAuthorized: 'Geen bevoegdheid',
                    notCharging: 'Niet opladen',
                    notReadyForCharging: 'Niet klaar voor opladen',
                    overviewChargingStations: 'Overzicht laadstations',
                    prioritization: 'Prioritering',
                    readyForCharging: 'Klaar om op te laden',
                    starting: 'Beginnend',
                    status: 'Staat',
                    totalCharge: 'Totale lading',
                    totalChargingPower: 'Totaal laadvermogen',
                    unknown: 'Onbekend',
                    unplugged: 'Unplugged',
                    Administration: {
                        carAdministration: 'Auto administratie',
                        customCarInfo: 'Als dit het geval is, kan uw auto alleen efficiënt worden geladen vanaf een bepaalde output. Met deze knop is dit opgenomen in uw configuratie-opties en in het automatisch laden.',
                        renaultZoe: 'Wordt een Renault Zoe voornamelijk op dit laadstation geladen?'
                    },
                    NoConnection: {
                        description: 'Hij kon niet op het laadstation worden aangesloten.',
                        help1_1: 'Het IP-adres van het laadstation verschijnt bij het opnieuw inschakelen',
                        help1: 'Controleer of het laadstation is ingeschakeld en via het netwerk kan worden bereikt',
                    },
                    OptimizedChargeMode: {
                        info: 'In deze modus wordt de belasting van de auto aangepast aan de huidige productie en het huidige verbruik.',
                        minChargePower: 'Loading rate',
                        minCharging: 'Minimale vergoeding betalen',
                        minInfo: 'Als u wilt voorkomen dat de auto s nachts niet oplaadt, kunt u een minimale lading instellen.',
                        name: 'Geoptimaliseerd laden',
                        shortName: 'Automatisch',
                        ChargingPriority: {
                            car: 'Auto',
                            info: 'Afhankelijk van de prioriteit wordt het geselecteerde onderdeel eerst geladen',
                            storage: 'Opslagruimte',
                        }
                    },
                    ForceChargeMode: {
                        info: 'In deze modus wordt het laden van de auto afgedwongen, d.w.z. het is altijd gegarandeerd dat de auto wordt opgeladen, zelfs als het laadstation toegang moet hebben tot netstroom.',
                        maxCharging: 'Maximale laadstroom',
                        maxChargingDetails: 'Als de auto de ingevoerde maximale waarde niet kan laden, wordt het vermogen automatisch beperkt.',
                        name: 'Gedwongen laden',
                        shortName: 'handmatig',
                    }
                },
                Heatingelement: {
                    heatingelement: 'Verwarmingselement',
                    priority: 'Prioriteit',
                    activeLevel: 'Actieve level',
                    energy: 'Energie',
                    time: 'Tijd',
                    endtime: 'Einde tijd',
                    minimalEnergyAmount: 'Minimale hoeveelheid energie',
                    minimumRunTime: 'Minimale looptijd',
                    timeCountdown: 'Tijd om te beginnen',
                },
                HeatPump: {
                    aboveSoc: 'en over de staat van beschuldiging van',
                    belowSoc: 'en onder staat van beschuldiging van',
                    gridBuy: 'Vanaf netaankoop van',
                    gridSell: 'Van overtollige toevoer van',
                    lock: 'Slot',
                    moreThanHpPower: 'De waarde mag niet lager zijn dan het maximale vermogen van de warmtepomp',
                    normalOperation: 'Normale operatie',
                    relationError: 'Overwaarde inschakelcommando moet groter zijn dan aanbevolen inschakelwaarde',
                    switchOnCom: 'Inschakelcommando',
                    switchOnRec: 'Inschakeladvies',
                    undefined: 'Ongedefinieerd',
                    normalOperationShort: '',
                    switchOnComShort: '',
                    switchOnRecShort: '',
                }
            }
        },
        History: {
            activeDuration: 'actieve duur',
            beginDate: 'Selecteer startdatum',
            day: 'Dag',
            endDate: 'Selecteer einddatum',
            export: 'download als Excel-bestand',
            go: 'Ga!',
            lastMonth: 'Vorige maand',
            lastWeek: 'Vorige week',
            lastYear: 'Vorig jaar',
            month: 'Maand',
            noData: 'geen gegevens beschikbaar',
            otherPeriod: 'Andere periode',
            period: 'Periode',
            selectedDay: '{{value}}',
            selectedPeriod: 'Geselecteerde periode: ',
            today: 'Vandaag',
            week: 'Woche',
            year: 'Jaar',
            yesterday: 'Gisteren',
            sun: 'Zon',
            mon: 'Maa',
            tue: 'Din',
            wed: 'Woe',
            thu: 'Don',
            fri: 'Vri',
            sat: 'Zat',
            jan: 'Jan',
            feb: 'Feb',
            mar: 'Maa',
            apr: 'Apr',
            may: 'Mei',
            jun: 'Jun',
            jul: 'Jul',
            aug: 'Aug',
            sep: 'Sep',
            oct: 'Okt',
            nov: 'Nov',
            dec: 'Dec'
        },
        Config: {
            Index: {
                addComponents: 'Componenten installeren',
                adjustComponents: 'Componenten configureren',
                bridge: 'Verbindingen en apparaten',
                controller: 'Toepassingen',
                dataStorage: 'Gegevensopslag',
                executeSimulator: 'Simulatie uitvoeren',
                liveLog: 'Live System log',
                log: 'Log',
                manualControl: 'Handmatige bediening',
                renameComponents: 'Componenten hernoem',
                scheduler: 'Toepassingsschema',
                simulator: 'Simulator',
                systemExecute: 'Voer systeemopdracht uit',
                systemProfile: 'Systeemprofiel',
            },
            More: {
                manualCommand: 'Handmatig commando',
                manualpqPowerSpecification: 'Gespecificeerd vermogen',
                manualpqReset: 'Reset',
                manualpqSubmit: 'Toepassen',
                refuInverter: 'REFU inverter',
                refuStart: 'Start',
                refuStartStop: 'Inverter starten/ stoppen',
                refuStop: 'Stop',
                send: 'Verstuur',
            },
            Scheduler: {
                always: 'Altijd',
                class: 'Soort: ',
                contact: 'Dit zou niet mogen gebeuren. Neem contact op met <a href=\'mailto:{{value}}\'>{{value}}</a>.',
                newScheduler: 'New Schema...',
                notImplemented: 'Gegevens niet geïmplementeerd: ',
            },
            Log: {
                automaticUpdating: 'Automatisch updaten',
                level: 'Niveau',
                message: 'Bericht',
                source: 'Bron',
                timestamp: 'Tijdspit',
            },
            Controller: {
                app: 'App:',
                internallyID: 'Intern ID:',
                priority: 'Prioriteit: ',
            },
            Bridge: {
                newConnection: 'Nieuwe verbinding...',
                newDevice: 'Nieuw apparaat…',
            }
        }
    },
    About: {
        build: 'Versie',
        contact: 'Voor meer informatie of suggesties over het systeem, neem contact op met het team via <a href=\'mailto:{{value}}\'>{{value}}</a>.',
        currentDevelopments: 'Huidige ontwikkelingen',
        developed: 'Deze gebruikersinterface is ontwikkeld als open-source-software.',
        language: 'Selecteer taal: ',
        sourcecode: 'Broncode',
        ui: 'Gebruikersinterface voor OpenEMS',
    },
    Notifications: {
        authenticationFailed: 'Geen verbinding.Autorisatie mislukt.',
        closed: 'Verbinding beëindigd.',
        failed: 'Verbinding mislukt.',
        loggedIn: 'Aangemeld.',
        loggedInAs: 'Aangemeld als gebruiker {{ value } }.', // (value = Benutzername)
    }
}

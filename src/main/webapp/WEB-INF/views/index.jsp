<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alemach
  Date: 06.02.2020
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content="Time series analysis with FFNN"/>
    <meta name="Aleksander Machaj" content="Time series analysis with FFNN"/>
    <title>Light Curve</title>
    <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed" style="background: url('/resources/assets/img/stars-1257860.jpg'); background-size: cover ">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="index.html">Light Curve</a>
    <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button
    ><!-- Navbar Search-->
    <!-- Navbar-->

</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <div class="sb-sidenav-menu-heading"></div>
                    <a class="nav-link" href="<c:url value="/"/>">
                        <div class="sb-nav-link-icon"></div>
                        Home</a>
                    <a class="nav-link" href="<c:url value="/training"/>">
                        <div class="sb-nav-link-icon"></div>
                        Training</a>
                    <a class="nav-link" href="<c:url value="/classification"/>">
                        <div class="sb-nav-link-icon"></div>
                        Classify</a>

                </div>
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">
                <h1 class="mt-4" style="color: white">Mission overview</h1>
                <div class="row" style="color: white">
                    <div class="static-landing-page">
                        <h1></h1>
                        <h2>Kepler</h2>

                        <p>The centuries-old quest for other worlds like our Earth has been rejuvenated by the intense excitement and popular interest surrounding the discovery of hundreds of planets orbiting other stars. There is now clear evidence for substantial numbers of three types of exoplanets; gas giants, hot-super-Earths in short period orbits, and ice giants. The challenge now is to find terrestrial planets (i.e., those one half to twice the size of the Earth), especially those in the habitable zone of their stars where liquid water might exist on the surface of the planet.</p>

                        <p>The Kepler Mission is specifically designed to survey our region of the Milky Way galaxy to discover hundreds of Earth-size and smaller planets in or near the habitable zone&nbsp;and determine the fraction of the hundreds of billions of stars in our galaxy that might have such planets.</p>

                        <div class="dnd-atom-wrapper type-image context-side_promo" contenteditable="false">
                            <div class="dnd-drop-wrapper"><!-- scald=243199:side_promo {"link":""} -->
                                <img typeof="foaf:Image" src="/resources/assets/img/284576main_kepler-transit-with-graph_946-710.jpg?itok=16UZEu0k" alt="The Transit Method of Detecting Extrasolar Planets" title="" width="320" height="282"><!-- END scald=243199 -->
                            </div>

                            <div class="dnd-legend-wrapper" contenteditable="false">
                                <div class="caption">When a planet crosses in front of its star as viewed by an observer, the event is called a transit. Transits by terrestrial planets produce a small change in a star's brightness of about 1/10,000 (100 parts per million, ppm), lasting for 2 to 16 hours.</div>

                                <div class="credits">Credits: NASA Ames</div>

                                <div class="link"><a href=""></a></div>
                            </div>
                        </div>
                        <h3>Kepler Science</h3>

                        <p></p>

                        <p>The scientific objective of the Kepler Mission is to explore the structure and diversity of planetary systems. This is achieved by surveying a large sample of stars to:</p>

                        <ul>
                            <li>Determine the percentage of terrestrial and larger planets that are in or near the habitable zone of a wide variety of stars</li>
                            <li>Determine the distribution of sizes and shapes of the orbits of these planets</li>
                            <li>Estimate how many planets there are in multiple-star systems</li>
                            <li>Determine the variety of orbit sizes and planet&nbsp;reflectivities, sizes, masses and densities of short-period giant planets</li>
                            <li>Identify additional members of each discovered planetary system using other techniques</li>
                            <li>Determine the properties of those stars that harbor planetary systems.</li>
                        </ul>
                        &nbsp;
                        <h3>The Transit Method of Detecting Extrasolar Planets</h3>

                        <p>When a planet passes in front of a star as viewed from Earth, the event is called a “transit”. On Earth, we can observe an occasional Venus or Mercury transit. These events are seen as a small black dot creeping across the Sun—Venus or Mercury blocks sunlight as the planet moves between the Sun and us. Kepler finds planets by looking for tiny dips in the brightness of a star when a planet crosses in front of it—we say the planet transits the star.</p>

                        <p>Once detected, the planet's orbital size can be calculated from the period (how long it takes the planet to orbit once around the star) and the mass of the star using Kepler's Third Law of planetary motion. The size of the planet is found from the depth of the transit (how much the brightness of the star drops) and the size of the star. From the orbital size and the temperature of the star, the planet's characteristic temperature can be calculated. From this the question of whether or not the planet is habitable (not necessarily inhabited) can be answered.</p>

                        <div class="dnd-atom-wrapper type-image context-side_promo" contenteditable="false">
                            <div class="dnd-drop-wrapper"><!-- scald=243201:side_promo {"link":""} --><img typeof="foaf:Image" src="/resources/assets/img/284778main_fov-kepler_946-710.jpg?itok=xhQq1pUT" alt="Kepler's Field Of View In Targeted Star Field" title="" width="320" height="385"><!-- END scald=243201 --></div>

                            <div class="dnd-legend-wrapper" contenteditable="false">
                                <div class="caption">Kepler's Field Of View In Targeted Star Field</div>

                                <div class="credits">Credits: NASA Ames</div>

                                <div class="link"><a href=""></a></div>
                            </div>
                        </div>

                        <h3>Target Field of View</h3>

                        <p>Since transits only last a fraction of a day, all the stars must be monitored continuously, that is, their brightnesses must be measured at least once every few hours. The ability to continuously view the stars being monitored dictates that the field of view (FOV) must never be blocked at any time during the year. Therefore, to avoid the Sun the FOV must be out of the ecliptic plane. The secondary requirement is that the FOV have the largest possible number of stars. This leads to the selection of a region in the Cygnus and Lyra constellations of our Galaxy as shown.</p>

                        <p>Learn more about the <a href="http://www.nasa.gov/kepler/overview/historybyborucki">history</a> of the mission from the account of Kepler visionary and principal investigator, William Borucki.</p>
                        &nbsp;

                        <hr>
                        <h2>Kepler's second mission: K2</h2>

                        <div class="dnd-atom-wrapper type-image context-side_image" contenteditable="false">
                            <div class="dnd-drop-wrapper"><!-- scald=278723:side_image {"link":"\/sites\/default\/files\/thumbnails\/image\/k2-ecliptic-30may-sc_facing-f1-labels.jpg"} --><a href="/sites/default/files/thumbnails/image/k2-ecliptic-30may-sc_facing-f1-labels.jpg"><img typeof="foaf:Image" src="/resources/assets/img/k2-ecliptic-30may-sc_facing-f1-labels.jpg?itok=SxuEFQKS" alt="K2 mission search fields" title="" width="320" height="160"></a><!-- END scald=278723 --></div>

                            <div class="dnd-legend-wrapper" contenteditable="false">
                                <div class="caption">The Kepler spacecraft, operating as the K2 mission, will spend three years observing a ribbon of the sky (blue line) as it orbits the sun. Roughly every 80 days, the spacecraft will pan to a new field of view (blue stamp) aligned with the plane of the solar system.</div>

                                <div class="credits">Credits: NASA Ames/W. Stenzel</div>

                                <div class="link"></div>
                            </div>
                        </div>

                        <p>The <a href="http://www.nasa.gov/content/nasa-ends-attempts-to-fully-recover-kepler-spacecraft-potential-new-missions-considered/">loss</a> of a second of the four reaction wheels on board the Kepler spacecraft in May 2013 brought an end to Kepler's four-year science mission to continuously monitor more than 150,000 stars to search for transiting exoplanets. Developed over the months following this failure, the <a href="http://adsabs.harvard.edu/abs/2014PASP..126..398H">K2 mission</a> represents a new concept for spacecraft operations that enables continued scientific observations with the Kepler space telescope. K2 became fully operational in May 2014 and is expected to continue operating until 2017 or 2018.</p>

                        <p>Using the transit method to detect brightness changes, the K2 mission entails a series of sequential observing <a href="https://keplerscience.arc.nasa.gov/k2-fields.html">"Campaigns"</a> of fields distributed around the ecliptic plane and offers a photometric precision <a href="https://keplerscience.arc.nasa.gov/k2-observing.html#fine-point-photometric-precision">approaching that of the original Kepler mission</a>. Operating in the ecliptic plane minimizes the torque exerted on the spacecraft by <a href="http://www.nasa.gov/content/ames/kepler-mission-manager-update-k2-has-been-approved">solar wind pressure</a>, reducing pointing drift to the point where spacecraft attitude can effectively be controlled through a combination of thrusters and the two remaining reaction wheels. Each campaign is therefore limited by sun angle constraints to a duration of approximately 80 days.</p>

                        <p><strong>Scientific motivations</strong></p>

                        <p>K2 is a community-driven mission. All K2 targets are proposed by the community through the <a href="https://keplerscience.arc.nasa.gov/k2-proposing-targets.html">Guest Observer program</a>. The scientific potential spans a wide range of astrophysics. It is anticipated that K2 will:</p>

                        <ul>
                            <li>Provide a yield of hot planets around bright stars for follow-up transit spectroscopy to facilitate rapid advances in the characterization of exoplanet atmospheres.</li>
                            <li>Provide a yield of small planets around bright, small stars to facilitate the most precise follow-up measurements to date of masses, densities and compositions.</li>
                            <li>Identify locations and characteristics of potentially-habitable planets around bright M-dwarfs in the solar neighborhood.</li>
                            <li>Determine if hot gas giants exist around young stars, or whether they migrate to small orbits at a later epoch by tidal or other interactions.</li>
                            <li>Determine the relationship between stellar structure, rotation and activity within stellar associations over a range of ages and metallicity.</li>
                            <li>Identify the progenitors of Type Ia supernovae from photometric structure in the rise to outburst maximum.</li>
                            <li>Discover and characterize binary stars within open clusters and stellar associations.</li>
                            <li>Characterize internal stellar structure and fundamental properties of stars using the tools of asteroseismology.</li>
                            <li>Provide a large, regular cadence survey of AGN activity in the optical bandpass.</li>
                            <li>Participate in multi-mission, multi-band monitoring campaigns of ecliptic targets along with other space-based hardware or ground-based telescopes.<br>
                                &nbsp;
                            </li>
                        </ul>
                        <div class="dnd-atom-wrapper type-video context-full_width">
                            <div class="dnd-drop-wrapper"><!-- scald=309392:full_width -->
                                <div class="scald-youtube-wrapper" style="padding-bottom: 56.25%">
                                    <iframe title="The Legacy of NASA's Kepler Space Telescope: More Planets Than Stars" src="//www.youtube.com/embed/3yij1rJOefM?rel=0" allowfullscreen="" style="max-width: 100%" width="100%" height="720" frameborder="0"></iframe>
                                </div><!-- END scald=309392 --></div>

                            <div class="dnd-legend-wrapper" contenteditable="false">
                                <div class="caption"></div>

                                <div class="credits"></div>

                                <div class="link"><a href=""></a></div>
                            </div>
                        </div>
                        &nbsp;

                        <p></p>
                    </div>
                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Aleksander Machaj</div>
                    <div>
                        <a href="https://xkcd.com/1838/">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/resources/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="/resources/dist/assets/demo/chart-area-demo.js"></script>
<script src="/resources/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="/resources/assets/demo/datatables-demo.js"></script>
</body>
</html>

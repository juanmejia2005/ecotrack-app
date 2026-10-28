/**
 * EcoTrack — Interactive Landing Page Script
 * Powers the interactive smartphone simulator, eco-calculator, architecture visualizer and theme manager.
 */

document.addEventListener('DOMContentLoaded', () => {
    initThemeToggle();
    initMobileMenu();
    initPhoneSimulator();
    initImpactCalculator();
    initArchitectureExplorer();
    initFaqAccordion();
    initScrollSpy();
});

/* ==========================================================================
   1. Theme Management (Dark / Light)
   ========================================================================== */
function initThemeToggle() {
    const toggleBtn = document.getElementById('theme-toggle');
    const html = document.documentElement;

    const savedTheme = localStorage.getItem('ecotrack-theme') || 'dark';
    html.setAttribute('data-theme', savedTheme);

    if (toggleBtn) {
        toggleBtn.addEventListener('click', () => {
            const currentTheme = html.getAttribute('data-theme');
            const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
            html.setAttribute('data-theme', newTheme);
            localStorage.setItem('ecotrack-theme', newTheme);
        });
    }
}

/* ==========================================================================
   2. Mobile Navigation Menu
   ========================================================================== */
function initMobileMenu() {
    const toggle = document.getElementById('mobile-toggle');
    const menu = document.getElementById('nav-menu');

    if (toggle && menu) {
        toggle.addEventListener('click', () => {
            menu.classList.toggle('open');
        });

        menu.querySelectorAll('.nav-link').forEach(link => {
            link.addEventListener('click', () => {
                menu.classList.remove('open');
            });
        });
    }
}

/* ==========================================================================
   3. Interactive Smartphone App Simulator
   ========================================================================== */
function initPhoneSimulator() {
    // Initial Simulated Eco-Reports
    let simulatedReports = [
        {
            id: 'rep-1',
            title: 'Contenedor de Plásticos Desbordado',
            description: 'El punto limpio de botellas PET superó su capacidad y obstaculiza la acera peatonal.',
            category: 'Puntos de Reciclaje',
            location: 'Cra 45 # 12-30 · Parque Central',
            status: 'Resuelto',
            userEmail: 'ana.martinez@ucn.edu.co',
            date: 'Hoy · 09:15'
        },
        {
            id: 'rep-2',
            title: 'Vertedero Clandestino de Escombros',
            description: 'Acumulación de escombros de construcción en lote baldío cerca al humedal comunal.',
            category: 'Vertedero Ilegal',
            location: 'Calle 70 con Av. 10',
            status: 'En Revisión',
            userEmail: 'carlos.gomez@ucn.edu.co',
            date: 'Hoy · 08:30'
        },
        {
            id: 'rep-3',
            title: 'Punto de Acopio de Pilas y Litio',
            description: 'Contenedor especializado instalado para recolección segura de baterías usadas.',
            category: 'Residuos Electrónicos',
            location: 'Estación Metro Sur · Entrada 2',
            status: 'Resuelto',
            userEmail: 'brigada.verde@ucn.edu.co',
            date: 'Ayer · 16:40'
        },
        {
            id: 'rep-4',
            title: 'Foco de Desechos Orgánicos y Podas',
            description: 'Ramas secas y residuos de poda acumulados sin recolección tras mantenimiento de jardines.',
            category: 'Compost Orgánico',
            location: 'Parque Norte · Sector 2',
            status: 'Pendiente',
            userEmail: 'laura.velez@ucn.edu.co',
            date: 'Ayer · 11:20'
        },
        {
            id: 'rep-5',
            title: 'Acumulación de Residuos Mixtos',
            description: 'Bolsas de basura sin clasificar dejadas en esquina comercial fuera de los horarios de recolección.',
            category: 'Basura General',
            location: 'Carrera 15 # 4-22',
            status: 'Pendiente',
            userEmail: 'vecino.alerta@ucn.edu.co',
            date: 'Hace 2 días · 19:10'
        }
    ];

    let currentCategoryFilter = 'Todos';

    const feedContainer = document.getElementById('sim-reports-list');
    const toast = document.getElementById('sim-toast');
    const filterChips = document.querySelectorAll('.sim-chip');
    const tabButtons = document.querySelectorAll('.sim-tab');
    const refreshBtn = document.getElementById('sim-btn-refresh');

    // Render Reports
    function renderReports() {
        if (!feedContainer) return;

        const filtered = currentCategoryFilter === 'Todos'
            ? simulatedReports
            : simulatedReports.filter(r => r.category === currentCategoryFilter);

        if (filtered.length === 0) {
            feedContainer.innerHTML = `
                <div style="text-align: center; padding: 30px 10px; color: var(--text-muted);">
                    <div style="font-size: 2rem; margin-bottom: 8px;">🔍</div>
                    <div style="font-size: 0.82rem; font-weight: 700; color: var(--text-primary); margin-bottom: 4px;">Sin reportes en esta categoría</div>
                    <div style="font-size: 0.72rem;">Sé el primero en reportar un punto ecológico en esta sección.</div>
                </div>
            `;
            return;
        }

        feedContainer.innerHTML = filtered.map(report => {
            const statusClass = report.status === 'Resuelto' 
                ? 'status-resolved' 
                : report.status === 'En Revisión' 
                    ? 'status-review' 
                    : 'status-pending';

            return `
                <div class="sim-report-card" data-id="${report.id}">
                    <div class="sim-card-header">
                        <span class="sim-category-badge">${report.category}</span>
                        <span class="sim-status-badge ${statusClass}" title="Haz clic para cambiar estado">${report.status}</span>
                    </div>
                    <div class="sim-card-title">${report.title}</div>
                    <div class="sim-card-desc">${report.description}</div>
                    <div class="sim-card-location">
                        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M20 10c0 6-8 12-8 12s-8-6-8-12a8 8 0 0 1 16 0Z"/><circle cx="12" cy="10" r="3"/></svg>
                        <span>${report.location}</span>
                    </div>
                    <div class="sim-card-footer">
                        <span>Por: ${report.userEmail.split('@')[0]}</span>
                        <span>${report.date}</span>
                    </div>
                </div>
            `;
        }).join('');

        // Attach Status Cycle Event on Cards
        feedContainer.querySelectorAll('.sim-status-badge').forEach(badge => {
            badge.addEventListener('click', (e) => {
                e.stopPropagation();
                const card = badge.closest('.sim-report-card');
                const id = card.getAttribute('data-id');
                const report = simulatedReports.find(r => r.id === id);

                if (report) {
                    // Cycle: Pendiente -> En Revisión -> Resuelto -> Pendiente
                    if (report.status === 'Pendiente') report.status = 'En Revisión';
                    else if (report.status === 'En Revisión') report.status = 'Resuelto';
                    else report.status = 'Pendiente';

                    showToast(`¡Estado actualizado a "${report.status}" en Firestore!`);
                    renderReports();
                }
            });
        });
    }

    // Show Notification Toast inside phone
    function showToast(message) {
        if (!toast) return;
        toast.textContent = message;
        toast.classList.add('show');
        setTimeout(() => {
            toast.classList.remove('show');
        }, 2200);
    }

    // Category Filter Selection
    filterChips.forEach(chip => {
        chip.addEventListener('click', () => {
            filterChips.forEach(c => c.classList.remove('active'));
            chip.classList.add('active');
            currentCategoryFilter = chip.getAttribute('data-category');
            renderReports();
        });
    });

    // In-Phone Tab Navigation
    tabButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            tabButtons.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');

            const tab = btn.getAttribute('data-tab');
            document.querySelectorAll('.sim-screen-view').forEach(view => {
                view.classList.remove('active');
            });

            const activeView = document.getElementById(`sim-view-${tab}`);
            if (activeView) activeView.classList.add('active');
        });
    });

    // In-Phone Create Report Form Submission
    const submitBtn = document.getElementById('sim-btn-submit-report');
    if (submitBtn) {
        submitBtn.addEventListener('click', () => {
            const titleInput = document.getElementById('sim-input-title');
            const categorySelect = document.getElementById('sim-select-category');
            const locationInput = document.getElementById('sim-input-location');
            const descInput = document.getElementById('sim-input-description');

            const title = titleInput ? titleInput.value.trim() : '';
            const location = locationInput ? locationInput.value.trim() : '';
            const description = descInput ? descInput.value.trim() : '';
            const category = categorySelect ? categorySelect.value : 'Puntos de Reciclaje';

            if (!title || !location || !description) {
                showToast('⚠️ Completa los campos obligatorios');
                return;
            }

            // Create New Report object
            const newReport = {
                id: `rep-${Date.now()}`,
                title: title,
                description: description,
                category: category,
                location: location,
                status: 'Pendiente',
                userEmail: 'ciudadano@ucn.edu.co',
                date: 'Justo ahora'
            };

            // Add to top of array
            simulatedReports.unshift(newReport);

            // Clear inputs
            if (titleInput) titleInput.value = '';
            if (locationInput) locationInput.value = '';
            if (descInput) descInput.value = '';

            // Switch back to Feed Tab and render
            const feedTab = document.querySelector('.sim-tab[data-tab="feed"]');
            if (feedTab) feedTab.click();

            showToast('✅ ¡Reporte publicado y sincronizado!');
            renderReports();
        });
    }

    // Refresh Button Event
    if (refreshBtn) {
        refreshBtn.addEventListener('click', () => {
            showToast('🔄 Snapshot Listener reactivo actualizado');
            renderReports();
        });
    }

    // Initial render
    renderReports();
}

/* ==========================================================================
   4. Interactive Eco-Impact Calculator
   ========================================================================== */
function initImpactCalculator() {
    const slider = document.getElementById('citizen-slider');
    const citizenDisplay = document.getElementById('calc-citizen-display');

    const wasteNum = document.getElementById('calc-waste-num');
    const co2Num = document.getElementById('calc-co2-num');
    const cleanedNum = document.getElementById('calc-cleaned-num');
    const timeNum = document.getElementById('calc-time-num');

    if (!slider) return;

    const formatter = new Intl.NumberFormat('es-CO');

    function updateCalculator() {
        const citizens = parseInt(slider.value, 10);
        if (citizenDisplay) citizenDisplay.textContent = formatter.format(citizens);

        // Algorithmic Estimations
        // ~ 24 kg recyclable waste diverted per citizen/month
        const waste = Math.round(citizens * 24);
        // ~ 18 kg CO2 emissions prevented per citizen/month
        const co2 = Math.round(citizens * 18);
        // ~ 1 clandestine dump cleaned per 32 active reporting citizens
        const cleaned = Math.max(1, Math.round(citizens * 0.032));
        // ~ 4.5 bureaucratic hours saved per citizen/year vs traditional web forms
        const hours = Math.round(citizens * 4.5);

        if (wasteNum) wasteNum.textContent = formatter.format(waste);
        if (co2Num) co2Num.textContent = formatter.format(co2);
        if (cleanedNum) cleanedNum.textContent = formatter.format(cleaned);
        if (timeNum) timeNum.textContent = formatter.format(hours);
    }

    slider.addEventListener('input', updateCalculator);
    updateCalculator();
}

/* ==========================================================================
   5. Interactive Architecture Visualizer
   ========================================================================== */
function initArchitectureExplorer() {
    const blocks = document.querySelectorAll('.arch-block');
    const detailTitle = document.getElementById('arch-detail-title');
    const detailBody = document.getElementById('arch-detail-body');

    const techInfo = {
        client: {
            title: '📱 Cliente Móvil Android (Jetpack Compose & Material 3)',
            body: 'Desarrollado 100% en Kotlin 2.0 bajo el patrón Clean Architecture y MVVM. Emplea Unidirectional Data Flow (UDF) con StateFlow y corrutinas. La interfaz declarativa no posee dependencias directas con el SDK de Firestore, comunicándose exclusivamente a través de la interfaz abstracta ReportRepository.'
        },
        auth: {
            title: '🛡️ Capa de Identidad (Firebase Authentication)',
            body: 'Gestiona la identidad de cada ciudadano mediante JSON Web Tokens (JWT) con firma criptográfica RFC 7519. Garantiza que ninguna credencial sensible quede expuesta en el dispositivo local y renueva silenciosamente las sesiones en segundo plano a través de Google Play Services.'
        },
        firestore: {
            title: '⚡ Base de Datos Distribuida (Cloud Firestore NoSQL)',
            body: 'Almacena la colección /eco_reports con réplicas multirregión y sharding automático. Utiliza Snapshot Listeners basados en gRPC y callbackFlow de Kotlin para entregar actualizaciones bidireccionales en sub-segundos sin recurrir a costosas llamadas periódicas (polling).'
        },
        extensions: {
            title: '🚀 Extensibilidad Serverless (Cloud Functions & Edge AI)',
            body: 'Ruta de escalamiento para la incubación del semillero: triggers en segundo plano que despachan notificaciones automáticas a empresas municipales de aseo y soporte para modelos de visión artificial on-device (TensorFlow Lite) para clasificación de residuos.'
        }
    };

    blocks.forEach(block => {
        block.addEventListener('click', () => {
            blocks.forEach(b => b.classList.remove('active'));
            block.classList.add('active');

            const key = block.getAttribute('data-component');
            const info = techInfo[key];

            if (info && detailTitle && detailBody) {
                detailTitle.textContent = info.title;
                detailBody.textContent = info.body;
            }
        });
    });
}

/* ==========================================================================
   6. FAQ Accordion
   ========================================================================== */
function initFaqAccordion() {
    const items = document.querySelectorAll('.faq-item');

    items.forEach(item => {
        const question = item.querySelector('.faq-question');
        const answer = item.querySelector('.faq-answer');

        if (question && answer) {
            question.addEventListener('click', () => {
                const isActive = item.classList.contains('active');

                // Close all other items
                items.forEach(other => {
                    other.classList.remove('active');
                    const otherAnswer = other.querySelector('.faq-answer');
                    if (otherAnswer) otherAnswer.style.maxHeight = null;
                });

                // Toggle current
                if (!isActive) {
                    item.classList.add('active');
                    answer.style.maxHeight = `${answer.scrollHeight}px`;
                }
            });
        }
    });
}

/* ==========================================================================
   7. Scroll Spy for Navigation Links
   ========================================================================== */
function initScrollSpy() {
    const sections = document.querySelectorAll('section[id]');
    const navLinks = document.querySelectorAll('.nav-link');

    window.addEventListener('scroll', () => {
        let current = '';
        const scrollPosition = window.pageYOffset + 120;

        sections.forEach(section => {
            const sectionTop = section.offsetTop;
            const sectionHeight = section.offsetHeight;
            if (scrollPosition >= sectionTop && scrollPosition < sectionTop + sectionHeight) {
                current = section.getAttribute('id');
            }
        });

        navLinks.forEach(link => {
            link.classList.remove('active');
            if (link.getAttribute('href') === `#${current}`) {
                link.classList.add('active');
            }
        });
    });
}
